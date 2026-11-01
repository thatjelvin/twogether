package com.twogether.app.feature.play

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
fun PlayScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Spacing.screenPadding),
        verticalArrangement = Arrangement.spacedBy(Spacing.medium),
    ) {
        Text(
            text = "Play",
            style = MaterialTheme.typography.displayLarge,
        )
        Text(
            text = "Live co-op date nights are coming later.",
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}
