import { onCall } from "firebase-functions/v2/https";

export const ping = onCall({ region: "us-central1" }, async () => ({ ok: true }));
