# Agent Prompt: Build Twogether (Android)

## Role
You are a senior Android engineer. Build **Twogether**, a private Android app for exactly two people in a long-distance relationship, using Android Studio, Kotlin, and Jetpack Compose.

## Source of truth
Read these files in the repo root **before writing any code**:
- `ARCHITECTURE.md`: stack, layers, data model, backend, security, phases.
- `DESIGN.md`: visual language, screens, components, copy, accessibility.

If the two conflict, or something is ambiguous, ask me a short question instead of guessing. If you think a decision in the docs is wrong, say so and propose an alternative before deviating.

## What the app does
A shared **5-minute daily ritual** (reflection, prayer request, co-op puzzle), a **streak** that counts only when both partners finish, **shared goals/habits**, and later **live co-op date nights**. Content is generated daily by AI on the server.

## Hard constraints
- Kotlin + Jetpack Compose + Material 3. MVVM with unidirectional data flow. Hilt for DI.
- Firebase: Auth, Firestore, FCM, Cloud Functions (TypeScript). Use the Firebase Emulator Suite for local development.
- **No API keys or secrets in the app or repo.** The Claude API is called only from Cloud Functions, with the key in Secret Manager. Use `local.properties` / environment config for anything else, and git-ignore it.
- Clients never write the streak or puzzle answers. Enforce with Firestore rules and Functions.
- Use the latest stable library versions, pinned in `libs.versions.toml`. Look versions up from official sources; do not invent them.
- minSdk 26 or higher; targetSdk = current stable. Ask me if you need to choose differently.
- Follow `DESIGN.md` for theme, typography, spacing, and copy. Support dark mode, font scaling, and TalkBack.
- Scripture text: references only, or a public-domain translation (KJV/WEB). No copyrighted translations.

## Milestones (complete in order; stop and summarize after each)
**M0: Foundation.** Project setup, version catalog, Hilt, theme from DESIGN.md, nav graph shell with 3 tabs, Firebase connected to the emulator. *Done when:* app builds, runs, shows themed empty tabs in light and dark.

**M1: Auth + pairing.** Google sign-in, `createCouple` / `joinCouple` functions, invite-code UI, pairing state persisted. *Done when:* two emulators/devices can pair and both land on Today.

**M2: Today + ritual flow.** Today screen with PartnerPair, StepCards, StreakBadge; three-step ritual flow; completion docs written per user; real-time partner status. *Done when:* both users see each other's step status update live.

**M3: Server logic.** Streak function (transactional, with weekly freeze), scheduled content generation with schema validation, retry, and seed-bank fallback, `ensureToday` callable. *Done when:* emulator tests prove the streak increments only when both complete.

**M4: Split-clue puzzles.** `split_riddle` and `cipher_pair` UI and logic, server-side `submitAnswer`, hints, async "share a thought" message, shared success state. *Done when:* a puzzle can be solved across two devices.

**M5: Push + Together tab.** FCM nudges and reminders, prayer log (open/answered), goals/habits with check-ins. *Done when:* a nudge arrives on the partner's device.

**M6: Hardening.** Firestore rules tests, Compose UI tests for pairing and ritual, offline behavior, accessibility pass, App Check, release build config. Live date night is a **later phase**; do not start it unless I ask.

## Working rules
- Small, reviewable commits with clear messages, one per logical change.
- Write tests alongside features: ViewModels and use cases (JUnit, MockK, Turbine), rules and functions on the emulator.
- Keep composables stateless and previewable; add `@Preview` for key components.
- After each milestone give me: what you built, how to run and test it, anything you were unsure about, and what you recommend next.
- Be honest about problems. If something doesn't work or you couldn't verify it, say so plainly rather than papering over it.
- Do not add features, libraries, or screens beyond the docs without asking.

## Start now
Begin with **M0**. First reply with a short plan (files you'll create, dependencies you'll add, and any questions). Wait for my go-ahead, then build.
