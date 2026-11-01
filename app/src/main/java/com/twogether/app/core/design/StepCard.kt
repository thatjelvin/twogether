package com.twogether.app.core.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.CardDefaults
import com.twogether.app.core.design.Spacing
import com.twogether.app.core.design.theme.NightIndigo
import com.twogether.app.core.design.theme.TwogetherTheme

enum class StepState { Pending, Done }

@Composable
fun StepDot(done: Boolean, label: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(12.dp)
            .background(
                color = if (done) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                shape = CircleShape,
            )
            .semantics { contentDescription = label },
    )
}

@Composable
fun StepCard(
    number: Int,
    title: String,
    youDone: Boolean,
    partnerDone: Boolean,
    yourName: String,
    partnerName: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = "Step $number, $title. You: " +
                    if (youDone) "done" else "not yet" +
                    ", $partnerName: " + if (partnerDone) "done" else "not yet"
            },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier
                .padding(Spacing.medium)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "$number",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(Modifier.width(Spacing.medium))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f),
            )
            Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(6.dp)) {
                StepDot(
                    done = youDone,
                    label = if (youDone) "You: done" else "You: not yet",
                )
                StepDot(
                    done = partnerDone,
                    label = if (partnerDone) "$partnerName: done" else "$partnerName: not yet",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StepCardPreview() {
    TwogetherTheme {
        Column {
            StepCard(
                number = 1,
                title = "Reflection",
                youDone = true,
                partnerDone = true,
                yourName = "Jelvin",
                partnerName = "Sarah",
            )
        }
    }
}
