package com.twogether.app.feature.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twogether.app.core.result.Result
import com.twogether.app.domain.usecase.CreateCouple
import com.twogether.app.domain.usecase.JoinCouple
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

sealed class PairingUiState {
    object Initial : PairingUiState()
    data class Creating(val inviteCode: String) : PairingUiState()
    data class Paired(val coupleId: String) : PairingUiState()
    data class Error(val message: String) : PairingUiState()
}

@Singleton
class PairingViewModel @Inject constructor(
    private val createCouple: CreateCouple,
    private val joinCouple: JoinCouple,
) : ViewModel() {

    var uiState by mutableStateOf<PairingUiState>(PairingUiState.Initial)
        private set

    fun onCreateCouple() {
        viewModelScope.launch {
            val result = createCouple()
            when (result) {
                is Result.Success -> uiState = PairingUiState.Creating(inviteCode = result.data.inviteCode ?: "")
                is Result.Error -> uiState = PairingUiState.Error(result.exception.message ?: "Create failed")
            }
        }
    }

    fun onJoinCouple(inviteCode: String) {
        viewModelScope.launch {
            val result = joinCouple(inviteCode)
            when (result) {
                is Result.Success -> uiState = PairingUiState.Paired(coupleId = result.data.coupleId)
                is Result.Error -> uiState = PairingUiState.Error(result.exception.message ?: "Join failed")
            }
        }
    }
}
