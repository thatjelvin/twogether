package com.twogether.app.feature.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twogether.app.core.result.Result
import com.twogether.app.domain.model.User
import com.twogether.app.domain.usecase.SignInWithGoogle
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

sealed class AuthUiState {
    object SignedOut : AuthUiState()
    data class SignedIn(val displayName: String?, val photoUrl: String?) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}

@Singleton
class AuthViewModel @Inject constructor(
    private val signInWithGoogle: SignInWithGoogle,
) : ViewModel() {

    var uiState by mutableStateOf<AuthUiState>(AuthUiState.SignedOut)
        private set

    fun onSignInWithGoogle(idToken: String) {
        viewModelScope.launch {
            val result = signInWithGoogle(idToken)
            handleSignInResult(result)
        }
    }

    fun handleSignInResult(result: Result<User>) {
        when (result) {
            is Result.Success -> uiState = AuthUiState.SignedIn(
                displayName = result.data.displayName,
                photoUrl = result.data.photoUrl,
            )
            is Result.Error -> uiState = AuthUiState.Error(
                message = result.exception.message ?: "Sign-in failed"
            )
        }
    }
}
