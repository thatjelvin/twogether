package com.twogether.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.twogether.app.feature.play.PlayScreen
import com.twogether.app.feature.today.TodayScreen
import com.twogether.app.feature.today.TodayViewModel
import com.twogether.app.feature.together.TogetherScreen

@Composable
fun TwogetherNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = TodayRoute,
        modifier = modifier,
    ) {
        composable<TodayRoute> {
            val todayViewModel: TodayViewModel = hiltViewModel()
            TodayScreen(viewModel = todayViewModel)
        }
        composable<TogetherRoute> {
            TogetherScreen()
        }
        composable<PlayRoute> {
            PlayScreen()
        }
    }
}
