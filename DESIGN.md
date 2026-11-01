# Twogether: Design

## 1. Feel
Warm, calm, intimate. Closer to a handwritten letter than a game app. Quiet by default, with small moments of delight when you and your partner both finish something. Never guilt-trippy: streaks encourage, they don't punish.

## 2. Principles
1. **Two people, one screen language.** Always show both partners' status side by side (avatar + state).
2. **Five minutes, then done.** The ritual ends with a clear, satisfying finish. No endless scroll.
3. **Waiting should feel connected.** If your partner hasn't finished, show a gentle "waiting for [name]" state with a nudge button, not an empty screen.
4. **Reverent where it matters.** Devotional screens are spacious and unhurried.
5. **One primary action per screen.**

## 3. Color (Material 3 roles)
Light theme:
| Role | Hex | Use |
|---|---|---|
| background | `#FFF8F1` | app background (warm cream) |
| surface | `#FFFFFF` | cards |
| surfaceVariant | `#F4E9DF` | chips, subtle fills |
| primary | `#B8475E` | main actions (dusk rose) |
| onPrimary | `#FFFFFF` | |
| secondary | `#2E3A59` | headers, deep accents (night indigo) |
| tertiary | `#D9A441` | streak, celebration (gold) |
| onSurface | `#2B2326` | body text |
| outline | `#CDBFB3` | dividers |
| error | `#B3261E` | errors |

Dark theme:
| Role | Hex |
|---|---|
| background | `#1B1619` |
| surface | `#251E22` |
| primary | `#F0A3B2` |
| secondary | `#AEBBE0` |
| tertiary | `#E8BE6A` |
| onSurface | `#F3E8EA` |

Each partner gets an **identity color** used for their avatar ring and progress dot: Partner A = rose, Partner B = indigo (user-swappable). Verify all text pairs meet WCAG AA contrast (4.5:1 body, 3:1 large).

## 4. Typography
- **Display / reflections:** a warm serif (e.g. Fraunces) for titles, verses, and reflection text.
- **UI / body:** a clean sans (e.g. DM Sans or Inter).
- Load via Compose downloadable Google Fonts, with a system fallback.

| Style | Font | Size / weight |
|---|---|---|
| Display | Serif | 32 / SemiBold |
| Title | Serif | 22 / Medium |
| Verse | Serif italic | 20 / Regular |
| Body | Sans | 16 / Regular |
| Label | Sans | 13 / Medium |

Minimum body size 16sp. Respect system font scaling up to 200%.

## 5. Shape, spacing, elevation
- 8dp spacing grid. Screen padding 20dp.
- Corner radius: cards 20dp, buttons 16dp, chips full.
- Mostly flat; use tonal surfaces instead of heavy shadows.

## 6. Navigation
Bottom bar, 3 tabs: **Today**, **Together**, **Play**. Settings lives behind the avatar on Today. Pairing/onboarding is a separate flow before the bar appears.

## 7. Screens

### 7.1 Onboarding and pairing
1. Welcome: logo, one-line promise, "Continue with Google."
2. Choose: **Create our space** or **Join with code**.
3. Create: shows a large 6-character code, a Share button, and "Waiting for your partner…" with a soft animation.
4. Join: 6-box code input, auto-advance, clear error states.
5. Set shared timezone and a daily reminder time.

### 7.2 Today (home)
```
[ avatar A ]──♥──[ avatar B ]     🔥 12-day streak
Good evening, Jelvin

┌ Today's ritual ──────────────┐
│ ① Reflection         ✓ ✓     │
│ ② Prayer request     ✓ ·     │
│ ③ Puzzle             · ·     │
│         [ Continue ]         │
└──────────────────────────────┘

Partner status line: "Waiting for [name]"  [ Nudge ]
```
Each step shows two dots (you / partner). The primary button resumes the next unfinished step.

### 7.3 Ritual flow (3 full-screen steps with a progress bar)
1. **Reflection:** scripture reference + passage, short reflection, one question. "Done" to continue.
2. **Prayer request:** single text field, "Share with [name]". Shows partner's request once posted. Tap to mark "praying for you."
3. **Puzzle:** see 7.4.
Finish: a gentle celebration screen (gold confetti, streak update). If the partner is not done, show "We'll let you know when [name] finishes."

### 7.4 Split-clue puzzle
- Top: puzzle title and difficulty dots.
- Card showing **only your clues**, labeled "What you see."
- Banner: "[Name] has different clues. Talk it through." with a button that opens a quick **share a thought** field (short text, sent to partner) so the puzzle works asynchronously.
- Answer field + Submit. Hints unlock after a wait or a tap, costing nothing but noted.
- Solve: both partners see a shared success moment.

### 7.5 Together tab
- Segmented: **Goals**, **Habits**, **Prayers**.
- Goals/Habits: list with both partners' check-in dots per day; weekly check-in card on Sundays.
- Prayers: open vs answered lists. "Mark answered" prompts an optional note. Answered items get a quiet gold marker.

### 7.6 Play tab (date night, phase 2)
- Lobby: pick a game, see whether your partner is online, "Invite" sends a push.
- In-game: minimal HUD, presence indicator, a quick-react bar (emoji) instead of chat.

### 7.7 Settings
Profile, shared timezone, reminder time, notifications, theme, AI privacy toggle (what is shared for personalization), sign out. Unpair requires a confirmation step.

## 8. Components
- **PartnerPair:** two avatars with identity-color rings and a heart between.
- **StepCard:** title, state, two dots.
- **StreakBadge:** gold flame/spark with count; pulses once when updated.
- **VerseCard:** serif italic on tonal surface.
- **WaitingBanner:** soft pulsing dot + nudge button.
- **CodeInput:** 6 boxes, auto-advance, paste support.
- Standard M3 buttons, text fields, bottom sheets, snackbars.

## 9. Motion
- 200 to 300ms standard easing for transitions.
- Celebrations are short (under 1.5s) and skippable.
- Respect system "remove animations."

## 10. Copy and tone
Warm, plain, short. Use names, not "your partner," once known. Examples:
- Waiting: "Waiting for Sarah. Send a little nudge?"
- Done: "You're both done for today."
- Missed day: "Yesterday slipped by. Your streak freeze covered it."
Never shame or use urgency language.

## 11. Accessibility
- Touch targets at least 48dp.
- TalkBack labels on every icon and on the dot indicators ("You: done, Sarah: not yet").
- Never rely on color alone: pair identity colors with initials and shapes.
- Support dark theme, font scaling, and reduced motion.

## 12. Empty, loading, error states
- Loading: skeleton shimmers on cards, not full-screen spinners.
- Offline: small chip "Offline, changes will sync."
- Content failed to generate: show a seed-bank card with no error shown to the user.

## 13. Assets
- App icon: two overlapping circles in rose and indigo on cream, gold spark at the overlap. Provide adaptive icon layers and a monochrome layer.
- Use Material Symbols (outlined) for icons.
