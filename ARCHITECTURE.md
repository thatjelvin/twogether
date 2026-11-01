# Twogether: Architecture

Working name: **Twogether**. A private Android app for exactly two people in a long-distance relationship. Core loop: a 5-minute daily ritual, co-op puzzles, shared goals, and a prayer log.

## 1. Principles
- **Two users only.** One couple per account pair. No public profiles, no feeds.
- **Native Android, Kotlin, Jetpack Compose.**
- **Secrets never ship in the APK.** AI calls happen server-side only.
- **Server-authoritative for shared state** (streaks, completions). Clients only write their own data.
- **Offline-tolerant.** Firestore offline cache on; UI never blocks on network for reads.

## 2. Stack
| Concern | Choice |
|---|---|
| Language / UI | Kotlin, Jetpack Compose, Material 3 |
| Architecture | MVVM + unidirectional data flow (UiState / UiEvent) |
| DI | Hilt |
| Navigation | Navigation Compose (type-safe routes) |
| Async | Coroutines + Flow |
| Auth | Firebase Auth (Google sign-in + email link) |
| Database | Cloud Firestore (realtime listeners, offline cache) |
| Push | Firebase Cloud Messaging |
| Backend | Cloud Functions for Firebase (TypeScript). Requires the Blaze plan |
| AI | Claude API, called only from Cloud Functions. Key in Secret Manager |
| Local prefs | Jetpack DataStore |
| Images | Coil |
| Testing | JUnit, MockK, Turbine, Compose UI tests, Firebase Emulator Suite |

Use the latest stable versions of everything and pin them in `gradle/libs.versions.toml`. Verify versions against official docs; do not guess.

## 3. Project layout (single Gradle module to start)
```
app/src/main/java/com/twogether/app/
  core/        design system, theme, common UI, util, result types
  data/        repositories (impl), Firestore data sources, DTOs, mappers
  domain/      models, repository interfaces, use cases
  feature/
    auth/      sign-in
    pairing/   create/join couple via invite code
    today/     home + daily ritual flow
    puzzle/    split-clue puzzle UI + logic
    together/  goals, habits, prayer log
    datenight/ live sessions (phase 2)
    settings/
  di/          Hilt modules
  navigation/  nav graph
functions/     Cloud Functions (TypeScript)
firestore.rules
firestore.indexes.json
```
Split into Gradle modules later only if build times demand it.

## 4. Layering rules
- `feature` depends on `domain` and `core`. Never on `data`.
- `data` implements `domain` interfaces. Firestore types never leak past `data`.
- ViewModels expose a single `StateFlow<UiState>` and accept `UiEvent`s. One-off effects go through a `Channel`.
- Composables are stateless where possible and take state + lambdas.

## 5. Firestore data model
```
users/{uid}
  displayName, photoUrl, fcmTokens[], coupleId, timezone

couples/{coupleId}
  memberIds: [uidA, uidB]
  inviteCode (null once paired), createdAt
  dayTimezone            // IANA tz used to define "today" for the pair
  streak: { current, best, lastCompletedDayKey, freezesLeft }

couples/{coupleId}/days/{dayKey}            // dayKey = yyyy-MM-dd in dayTimezone
  reflection: { title, body, scriptureRef, question }
  puzzleId
  generatedAt, source: "ai" | "seed"

couples/{coupleId}/days/{dayKey}/completions/{uid}
  reflectionDone, prayerPosted, puzzleDone, completedAt

couples/{coupleId}/prayers/{prayerId}
  authorId, text, createdAt, status: "open" | "answered", answeredAt, answeredNote

couples/{coupleId}/goals/{goalId}
  title, kind: "goal" | "habit", cadence, createdBy
couples/{coupleId}/goals/{goalId}/checkins/{dayKey_uid}

couples/{coupleId}/puzzles/{puzzleId}
  type, payloadA, payloadB, answerHash, hints[], solvedAt

couples/{coupleId}/sessions/{sessionId}     // live date night, phase 2
  type, state, presence{uid: ts}, createdAt
```
Sensitive fields: the puzzle `answerHash` is a salted hash; the plaintext answer is never sent to clients.

## 6. Pairing flow
1. User A signs in, taps **Create couple**. Function `createCouple` makes the couple doc and a 6-character invite code.
2. User B signs in, enters the code. Function `joinCouple` validates (code exists, couple has 1 member), adds B, nulls the code.
3. Both users get `coupleId` on their user doc. Any further join attempt is rejected.

## 7. Daily ritual and streaks
- The **ritual** is 3 steps: reflection, prayer request, puzzle round. Each step writes a flag to the user's own completion doc.
- A `onWrite` Cloud Function on completions checks whether both members have all three flags for the `dayKey`. If yes, it updates `couples/{id}.streak` in a transaction. **Clients never write the streak.**
- **Time zones are the hard part.** Partners are in different zones. The couple picks one `dayTimezone` at pairing (default: whichever is more convenient for both) so "today" is unambiguous. Each user still gets local-time reminders.
- One **streak freeze** per week (`freezesLeft`) so a single missed day doesn't erase progress.
- Open decision: whether to allow changing `dayTimezone` when one partner travels.

## 8. AI content pipeline (server-side)
- **Scheduled function** (runs shortly before each couple's day starts) generates the next day's content. Plus a callable `ensureToday` fallback if content is missing.
- Model name comes from an environment variable. Check the current model list in Anthropic's docs before choosing a default.
- Prompt asks for **strict JSON** matching a schema. The function validates it, retries once on failure, then falls back to a **seed bank** of pre-written content shipped in `functions/seed/`.
- **Scripture:** store references (e.g. "Psalm 23:1-3"). For quoted text, use a public-domain translation (KJV or WEB), not a copyrighted one.
- **Privacy:** only send the minimum needed to personalize (e.g. shared goal titles). Never send prayer text unless the couple opts in. Document this in Settings.
- Cache everything per day so cost stays near zero for a two-person app.

## 9. Co-op puzzles
Core mechanic: **split clues.** Each partner sees different information and must communicate to solve it.

AI-generated payload schema:
```json
{
  "type": "split_riddle",
  "title": "string",
  "payloadA": { "clues": ["string"] },
  "payloadB": { "clues": ["string"] },
  "answer": "string",
  "hints": ["string", "string"],
  "difficulty": 1
}
```
MVP puzzle types: `split_riddle` and `cipher_pair` (one holds the cipher key, one holds the encoded message). Answers are checked server-side via a callable `submitAnswer` so the plaintext never reaches clients. A solve marks `puzzleDone` for **both** members.

## 10. Live date night (phase 2)
- Firestore `sessions/{id}` doc with realtime listeners; presence via a heartbeat timestamp.
- Game state is a small event log or a single state doc updated via transactions.
- If latency proves a problem, reassess (Realtime Database is an option for presence).

## 11. Notifications (FCM)
- Partner completed their part: "Your turn."
- Daily reminder at a user-chosen local time.
- Streak at risk, sent late in the day only if one partner is done.
- Sent from Cloud Functions. Tokens stored on the user doc and pruned on failure. Request the Android 13+ notification permission in context, not at launch.

## 12. Security rules (sketch)
```
rules_version = '2';
service cloud.firestore {
  match /databases/{db}/documents {
    function inCouple(coupleId) {
      return request.auth != null &&
        request.auth.uid in get(/databases/$(db)/documents/couples/$(coupleId)).data.memberIds;
    }
    match /users/{uid} {
      allow read, write: if request.auth.uid == uid;
    }
    match /couples/{coupleId} {
      allow read: if inCouple(coupleId);
      allow write: if false; // functions only
      match /days/{d}              { allow read: if inCouple(coupleId); allow write: if false; }
      match /days/{d}/completions/{uid} {
        allow read: if inCouple(coupleId);
        allow write: if inCouple(coupleId) && request.auth.uid == uid;
      }
      match /prayers/{p}  { allow read, create: if inCouple(coupleId);
                            allow update: if inCouple(coupleId); }
      match /goals/{g}/{document=**} { allow read, write: if inCouple(coupleId); }
      match /puzzles/{p}  { allow read: if inCouple(coupleId); allow write: if false; }
    }
  }
}
```
Tighten per-field rules and test with the emulator before release. Enable App Check.

## 13. Offline and errors
- Reads come from the Firestore cache first. Writes queue automatically.
- Surface a small "offline" chip; never a blocking dialog.
- All repository calls return a `Result` type; map to user-friendly messages in the ViewModel.

## 14. Testing
- Unit tests for use cases and ViewModels (MockK, Turbine).
- Firestore rules and Functions tested against the **Emulator Suite**.
- Compose UI tests for the ritual flow and pairing.
- Manual two-device test checklist for pairing, completion sync, and push.

## 15. Build phases
1. **MVP:** auth, pairing, Today screen, daily ritual (reflection + prayer + split-riddle puzzle), server-side streak, push nudges.
2. **Together:** shared goals/habits with weekly check-in, answered-prayer log.
3. **Date night:** live co-op puzzles, presence.
4. **Polish:** reading plan, widgets, themes, onboarding, accessibility pass.

## 16. Risks and open decisions
- **Retention:** puzzle variety decides whether the ritual survives week three. Plan new puzzle types early.
- **Cloud cost/plan:** Functions need Blaze; at two users the AI and Firebase cost should be minimal, but set budget alerts.
- **Time zones:** `dayTimezone` policy above needs a final call.
- **Content quality:** AI puzzles can be unsolvable. Validate server-side and keep the seed bank as fallback.
- **Distribution:** a private APK or internal testing track is enough for two users.
