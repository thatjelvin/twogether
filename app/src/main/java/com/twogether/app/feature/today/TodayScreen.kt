package com.twogether.app.feature.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.twogether.app.core.design.PartnerPair
import com.twogether.app.core.design.Spacing
import com.twogether.app.core.design.StepCard
import com.twogether.app.core.design.StreakBadge
import com.twogether.app.domain.model.RitualStep

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayScreen(
    viewModel: TodayViewModel,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.uiState

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Twogether") }) },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(Spacing.screenPadding),
            verticalArrangement = Arrangement.spacedBy(Spacing.medium),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = Spacing.small),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PartnerPair(
                    yourName = state.yourName ?: "You",
                    partnerName = state.partnerName ?: "Partner",
                    modifier = Modifier.weight(1f),
                )
                StreakBadge(count = state.streakCount)
            }

            val greeting = state.yourName?.let { "Good evening, $it" } ?: "Good evening"
            Text(
                text = greeting,
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = "Today's ritual",
                style = MaterialTheme.typography.titleMedium,
            )

            StepCard(
                number = 1,
                title = "Reflection",
                youDone = state.yourCompletions?.reflectionDone == true,
                partnerDone = state.partnerCompletions?.reflectionDone == true,
                yourName = state.yourName ?: "You",
                partnerName = state.partnerName ?: "Partner",
                modifier = Modifier.fillMaxWidth().clickable {
                    viewModel.onStepDone(RitualStep.REFLECTION)
                },
            )
            StepCard(
                number = 2,
                title = "Prayer request",
                youDone = state.yourCompletions?.prayerPosted == true,
                partnerDone = state.partnerCompletions?.prayerPosted == true,
                yourName = state.yourName ?: "You",
                partnerName = state.partnerName ?: "Partner",
                modifier = Modifier.fillMaxWidth().clickable {
                    viewModel.onStepDone(RitualStep.PRAYER)
                },
            )
            StepCard(
                number = 3,
                title = "Puzzle",
                youDone = state.yourCompletions?.puzzleDone == true,
                partnerDone = state.partnerCompletions?.puzzleDone == true,
                yourName = state.yourName ?: "You",
                partnerName = state.partnerName ?: "Partner",
                modifier = Modifier.fillMaxWidth().clickable {
                    viewModel.onStepDone(RitualStep.PUZZLE)
                },
            )

            val partnerDoneAll = state.partnerCompletions?.let {
                it.reflectionDone && it.prayerPosted && it.puzzleDone
            } ?: false
            if (!partnerDoneAll && state.partnerName != null) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = Spacing.small),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Spacing.small),
                ) {
                    Text(
                        text = "Waiting for ${state.partnerName}",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Button(onClick = { /* nudge */ }) {
                        Text(text = "Nudge")
                    }
                }
            }

            val nextStep = when {
                state.yourCompletions?.reflectionDone != true -> RitualStep.REFLECTION
                state.yourCompletions?.prayerPosted != true -> RitualStep.PRAYER
                state.yourCompletions?.puzzleDone != true -> RitualStep.PUZZLE
                else -> null
            }
            if (nextStep != null) {
                Button(
                    onClick = { viewModel.onStepDone(nextStep) },
                    modifier = Modifier.fillMaxWidth().padding(top = Spacing.small),
                ) {
                    Text(
                        text = "Continue ${label(nextStep)}",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            } else {
                Text(
                    text = "You're both done for today.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }

            state.error?.let { error ->
                Text(text = error, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

private fun label(step: RitualStep): String = when (step) {
    RitualStep.REFLECTION -> "reflection"
    RitualStep.PRAYER -> "prayer request"
    RitualStep.PUZZLE -> "puzzle"
}
