package com.twogether.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.twogether.app.core.design.theme.TwogetherTheme
import com.twogether.app.core.result.Result
import com.twogether.app.feature.auth.AuthViewModel
import com.twogether.app.feature.auth.PairingViewModel
import com.twogether.app.navigation.TwogetherApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var pairingViewModel: PairingViewModel

    private lateinit var googleSignInClient: GoogleSignInClient

    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data = result.data
            val task = data?.let { GoogleSignIn.getSignedInAccountFromIntent(it) }
            handleSignInResult(task)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        googleSignInClient = GoogleSignIn.getClient(
            this,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build(),
        )

        setContent {
            TwogetherTheme {
                TwogetherApp(
                    authViewModel = authViewModel,
                    pairingViewModel = pairingViewModel,
                    onSignInWithGoogle = { signInWithGoogle() },
                )
            }
        }
    }

    private fun signInWithGoogle() {
        val intent = googleSignInClient.signInIntent
        signInLauncher.launch(intent)
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>?) {
        val account = if (task != null && task.isSuccessful) {
            task.result
        } else {
            authViewModel.handleSignInResult(
                Result.Error(IllegalStateException("Sign-in was cancelled or failed"))
            )
            return
        }

        val idToken = account.idToken ?: run {
            authViewModel.handleSignInResult(
                Result.Error(IllegalStateException("No id token"))
            )
            return
        }
        authViewModel.onSignInWithGoogle(idToken)
    }
}
