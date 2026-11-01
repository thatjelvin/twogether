export const SEED_BANK: Record<string, any> = {
  reflection: {
    title: "A quiet moment",
    body: "Be still, and know that I am God.",
    scriptureRef: "Psalm 46:10",
    question: "What is one thing you are grateful for today?",
  },
  puzzle: {
    type: "split_riddle",
    title: "The whispered phrase",
    payloadA: { clues: ["I speak without a mouth", "I hear without ears"] },
    payloadB: { clues: ["I have no body, but I come alive with wind"] },
    answer: "an echo",
    hints: ["Think of sound that bounces back", "It repeats words you say"],
    difficulty: 1,
  },
};

export function seedReflection(dayKey: string) {
  return {
    dayKey,
    source: "seed",
    generatedAt: new Date().toISOString(),
    reflection: SEED_BANK.reflection,
    puzzleId: null,
  };
}

export function seedPuzzle(dayKey: string) {
  return {
    dayKey,
    source: "seed",
    generatedAt: new Date().toISOString(),
    reflection: null,
    puzzle: SEED_BANK.puzzle,
  };
}
