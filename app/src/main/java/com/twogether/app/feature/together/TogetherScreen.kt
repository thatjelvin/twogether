package com.twogether.app.feature.together

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.twogether.app.core.design.Spacing

@Composable
fun TogetherScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Spacing.screenPadding),
        verticalArrangement = Arrangement.spacedBy(Spacing.medium),
    ) {
        Text(
            text = "Together",
            style = MaterialTheme.typography.displayLarge,
        )
        Text(
            text = "Shared goals, habits, and prayers will live here.",
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}
