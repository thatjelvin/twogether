package com.twogether.app.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.ui.graphics.vector.ImageVector
import com.twogether.app.R

enum class TopLevelDestination(
    val route: Any,
    @StringRes val labelRes: Int,
    val icon: ImageVector,
) {
    Today(TodayRoute, R.string.tab_today, Icons.Outlined.Home),
    Together(TogetherRoute, R.string.tab_together, Icons.Outlined.Favorite),
    Play(PlayRoute, R.string.tab_play, Icons.Outlined.SportsEsports),
}
