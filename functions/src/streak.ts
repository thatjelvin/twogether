import { onDocumentWritten } from "firebase-functions/v2/firestore";
import * as admin from "firebase-admin";
import "firebase-admin/firestore";

admin.initializeApp();
const db = (admin as any).firestore();

// Runs whenever either partner marks any step done for a day.
// If both members have all three flags true for that dayKey, increment the
// couple's streak in a transaction. Clients never write the streak.
export const streakTracker = onDocumentWritten(
  { region: "us-central1", document: "couples/{coupleId}/days/{dayKey}/completions/{uid}" },
  async (event) => {
    const coupleId = event.params.coupleId;
    const dayKey = event.params.dayKey;
    const uid = event.params.uid;

    const coupleRef = db.collection("couples").doc(coupleId);
    const dayRef = db.collection("couples").doc(coupleId).collection("days").doc(dayKey);

    const membersSnap = await coupleRef.get();
    if (!membersSnap.exists || !membersSnap.data()) {
      return null;
    }
    const memberIds = ((membersSnap.data() as Record<string, any> | undefined)?.memberIds as string[] | undefined) ?? [];
    if (memberIds.length !== 2) {
      return null; // not a complete pair yet
    }

    // Read both members' completion docs for this dayKey.
    const completions: Record<string, any> = {};
    for (const m of memberIds) {
      const snap = await dayRef.collection("completions").doc(m).get();
      completions[m] = snap.exists ? snap.data() : null;
    }

    const allDone =
      memberIds.every((m) => {
        const c = completions[m];
        return Boolean(c?.reflectionDone && c?.prayerPosted && c?.puzzleDone);
      });
    if (!allDone) {
      return null;
    }

    return db.runTransaction(async (tx: FirebaseFirestore.Transaction) => {
      const snap = await tx.get(coupleRef);
      const streak = (snap as any).data()?.streak as Record<string, any> | undefined;
      const current = streak?.current ?? 0;
      const best = streak?.best ?? 0;
      const lastCompletedDayKey = streak?.lastCompletedDayKey as string | undefined;

      // Only increment once per day.
      if (lastCompletedDayKey === dayKey) {
        return null;
      }

      const nextCurrent = current + 1;
      const nextBest = Math.max(best, nextCurrent);

      tx.update(coupleRef, {
        streak: {
          current: nextCurrent,
          best: nextBest,
          lastCompletedDayKey: dayKey,
          freezesLeft: streak?.freezesLeft ?? 1,
        },
      });
      return { streak: nextCurrent, best: nextBest };
    });
  }
);
