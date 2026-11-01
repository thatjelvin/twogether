import { onCall } from "firebase-functions/v2/https";
import * as admin from "firebase-admin";
import "firebase-admin/firestore";

admin.initializeApp();
const db = (admin as any).firestore();

// Server-side answer check so the plaintext answer never reaches clients.
// Caller passes their guess; we hash-salt it and compare to the stored answerHash.
export const submitAnswer = onCall(
  { region: "us-central1" },
  async (request) => {
    if (!request.auth) {
      throw new Error("Unauthenticated");
    }
    const uid = request.auth.uid;
    const data = request.data as { coupleId?: string; puzzleId?: string; guess?: string } | undefined;
    const coupleId = data?.coupleId;
    const puzzleId = data?.puzzleId;
    const guess = data?.guess;

    const puzzleRef = db
      .collection("couples")
      .doc(coupleId)
      .collection("puzzles")
      .doc(puzzleId);
    const puzzleSnap = await puzzleRef.get();
    if (!puzzleSnap.exists) {
      throw new Error("Puzzle not found");
    }
    const answerHash = puzzleSnap.data()?.answerHash as string | undefined;
    if (!answerHash) {
      throw new Error("Puzzle has no answer configured");
    }

    const guessHash = await hashGuess(guess ?? "");
    const solved = guessHash === answerHash;
    if (solved) {
      await puzzleRef.update({ solvedAt: (admin as any).firestore.FieldValue.serverTimestamp() });
      // Mark puzzleDone for both members' completions for today.
      const today = new Date().toISOString().slice(0, 10);
      const coupleRef = db.collection("couples").doc(coupleId);
      const membersSnap = await coupleRef.get();
      const memberIds = (membersSnap.data()?.memberIds as string[] | undefined) ?? [];
      const batch = db.batch();
      for (const m of memberIds) {
        const compRef = coupleRef
          .collection("days")
          .doc(today)
          .collection("completions")
          .doc(m);
        batch.set(compRef, { puzzleDone: true }, { merge: true });
      }
      await batch.commit();
    }

    return { solved };
  }
);

// Deterministic salted hash (client never sees the salt).
async function hashGuess(guess: string): Promise<string> {
  const encoder = new TextEncoder();
  const data = encoder.encode(`twogether:${guess.toLowerCase().trim()}`);
  const buf = await crypto.subtle.digest("SHA-256", data);
  const arr = new Uint8Array(buf);
  return Array.from(arr, (b) => b.toString(16).padStart(2, "0")).join("");
}
