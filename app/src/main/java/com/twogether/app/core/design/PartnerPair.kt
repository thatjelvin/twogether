package com.twogether.app.core.design

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.twogether.app.core.design.theme.DuskRose
import com.twogether.app.core.design.theme.NightIndigo
import com.twogether.app.core.design.theme.TwogetherTheme

@Composable
fun Avatar(
    name: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    val initial = name.trim().take(1).uppercase().ifEmpty { "?" }
    Surface(
        modifier = modifier.size(48.dp),
        shape = CircleShape,
        color = color,
    ) {
        androidx.compose.foundation.layout.Box(
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = initial,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
            )
        }
    }
}

@Composable
fun PartnerPair(
    yourName: String,
    partnerName: String,
    modifier: Modifier = Modifier,
    yourColor: Color = DuskRose,
    partnerColor: Color = NightIndigo,
) {
    Row(
        modifier = modifier.semantics {
            contentDescription = "$yourName and $partnerName"
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Avatar(
            name = yourName,
            color = yourColor,
            modifier = Modifier.border(2.dp, yourColor, CircleShape),
        )
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 8.dp),
        )
        Avatar(
            name = partnerName,
            color = partnerColor,
            modifier = Modifier.border(2.dp, partnerColor, CircleShape),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PartnerPairPreview() {
    TwogetherTheme {
        PartnerPair(yourName = "Jelvin", partnerName = "Sarah")
    }
}
