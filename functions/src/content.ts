import { onCall } from "firebase-functions/v2/https";
import * as admin from "firebase-admin";
import "firebase-admin/firestore";
import { seedReflection, seedPuzzle } from "./seed";

admin.initializeApp();
const db = (admin as any).firestore();

export const ensureToday = onCall(
  { region: "us-central1" },
  async (request) => {
    if (!request.auth) {
      throw new Error("Unauthenticated");
    }
    const uid = request.auth.uid;

    const userDoc = await db.collection("users").doc(uid).get();
    if (!userDoc.exists) {
      throw new Error("User not found");
    }
    const coupleId = userDoc.data()?.coupleId as string | undefined;
    if (!coupleId) {
      throw new Error("Not paired yet");
    }

    // Resolve dayTimezone for "today"
    const coupleDoc = await db.collection("couples").doc(coupleId).get();
    const dayTimezone = (coupleDoc.data()?.dayTimezone as string | undefined) ?? "UTC";
    const now = new Date();
    const dayKey = now.toLocaleString("en-US", { timeZone: dayTimezone }).split(",")[0];

    const dayRef = db.collection("couples").doc(coupleId).collection("days").doc(dayKey);
    const daySnap = await dayRef.get();
    if (daySnap.exists) {
      return { dayKey, exists: true, source: daySnap.get("source") };
    }

    // Try AI-generated content; fall back to seed bank silently.
    let generated: any = null;
    try {
      generated = await generateContent(dayKey, coupleId);
    } catch {
      generated = null;
    }

    const reflection = generated?.reflection ?? seedReflection(dayKey).reflection;
    const puzzle = generated?.puzzle ?? seedPuzzle(dayKey).puzzle;

    await dayRef.set({
      reflectionTitle: reflection.title,
      reflectionBody: reflection.body,
      scriptureRef: reflection.scriptureRef,
      question: reflection.question,
      puzzleType: puzzle.type,
      puzzlePayloadA: puzzle.payloadA,
      puzzlePayloadB: puzzle.payloadB,
      puzzleHints: puzzle.hints,
      puzzleDifficulty: puzzle.difficulty,
      source: generated ? "ai" : "seed",
      generatedAt: (admin as any).firestore.FieldValue.serverTimestamp(),
    });

    return { dayKey, exists: true, source: generated ? "ai" : "seed" };
  }
);

async function generateContent(dayKey: string, coupleId: string): Promise<any> {
  const apiKey = process.env.CLAUDE_API_KEY;
  if (!apiKey) {
    throw new Error("Claude API key not configured");
  }
  const response = await fetch("https://api.anthropic.com/v1/messages", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "x-api-key": apiKey,
      "anthropic-version": "2023-06-01",
    },
    body: JSON.stringify({
      model: process.env.CLAUDE_MODEL ?? "claude-3-haiku-20240307",
      max_tokens: 500,
      messages: [
        {
          role: "user",
          content: `Generate daily reflection content for couple ${coupleId} on ${dayKey}. Return strict JSON with keys: reflection {title, body, scriptureRef, question} and puzzle {type, payloadA, payloadB, answer, hints, difficulty}. Only return JSON.`,
        },
      ],
    }),
  });
  if (!response.ok) {
    throw new Error(`Claude API error: ${response.status}`);
  }
  const text = (await response.json()) as { content: { text: string }[] };
  return JSON.parse(text.content[0].text);
}
