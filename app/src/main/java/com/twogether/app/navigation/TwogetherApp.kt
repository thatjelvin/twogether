package com.twogether.app.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.twogether.app.core.design.Spacing
import com.twogether.app.core.design.theme.TwogetherTheme
import com.twogether.app.feature.auth.AuthUiState
import com.twogether.app.feature.auth.AuthViewModel
import com.twogether.app.feature.auth.PairingUiState
import com.twogether.app.feature.auth.PairingViewModel
import com.twogether.app.feature.play.PlayScreen
import com.twogether.app.feature.today.TodayScreen
import com.twogether.app.feature.together.TogetherScreen

@Composable
fun TwogetherApp(
    authViewModel: AuthViewModel,
    pairingViewModel: PairingViewModel,
    onSignInWithGoogle: () -> Unit,
) {
    val navController = androidx.navigation.compose.rememberNavController()
    val entries = TopLevelDestination.entries
    val selectedIndex = remember { mutableStateOf(0) }

    val authUi = authViewModel.uiState
    val pairingUi = pairingViewModel.uiState

    val signedIn = authUi is AuthUiState.SignedIn
    val paired = pairingUi is PairingUiState.Paired

    if (!signedIn) {
        AuthScreenContent(onSignIn = onSignInWithGoogle)
    } else if (!paired) {
        PairingScreenContent(
            onCreate = { pairingViewModel.onCreateCouple() },
            onJoin = { code -> pairingViewModel.onJoinCouple(code) },
        )
    } else {
        Shell(
            navController = navController,
            selectedIndex = selectedIndex,
            entries = entries,
        )
    }
}

@Composable
private fun Shell(
    navController: NavHostController,
    selectedIndex: androidx.compose.runtime.MutableState<Int>,
    entries: List<TopLevelDestination>,
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                entries.forEachIndexed { index, destination ->
                    val selected = selectedIndex.value == index
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            selectedIndex.value = index
                            navController.navigate(destination.route)
                        },
                        icon = { Icon(destination.icon, contentDescription = null) },
                        label = { Text(stringResource(destination.labelRes)) },
                    )
                }
            }
        },
    ) { innerPadding ->
        TwogetherNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun AuthScreenContent(
    onSignIn: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.screenPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Twogether",
            style = MaterialTheme.typography.displayLarge,
        )
        Text(
            text = "A private space for you and your partner.",
            style = MaterialTheme.typography.bodyLarge,
        )
        Button(
            onClick = onSignIn,
            modifier = Modifier.padding(top = Spacing.large),
        ) {
            Text("Continue with Google")
        }
    }
}

@Composable
private fun PairingScreenContent(
    onCreate: () -> Unit,
    onJoin: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.screenPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Pair with your partner",
            style = MaterialTheme.typography.titleLarge,
        )
        Button(
            onClick = onCreate,
            modifier = Modifier.fillMaxWidth().padding(vertical = Spacing.small),
        ) {
            Text("Create our space")
        }
        JoinScreenContent(onJoin = onJoin)
    }
}

@Composable
private fun JoinScreenContent(
    onJoin: (String) -> Unit,
) {
    var code by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = Spacing.screenPadding, top = Spacing.medium, end = Spacing.screenPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = code,
            onValueChange = { newCode -> code = newCode },
            label = { Text(text = "Invite code") },
            singleLine = true,
        )
        Button(
            onClick = { onJoin(code) },
            enabled = code.length == 6,
            modifier = Modifier.padding(top = Spacing.small).fillMaxWidth(),
        ) {
            Text(text = "Join")
        }
    }
}
