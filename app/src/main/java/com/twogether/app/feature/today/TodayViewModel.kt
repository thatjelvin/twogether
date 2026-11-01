package com.twogether.app.feature.today

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twogether.app.core.result.Result
import com.twogether.app.domain.model.RitualStep
import com.twogether.app.domain.model.StepCompletion
import com.twogether.app.domain.repository.AuthRepository
import com.twogether.app.domain.repository.PairingRepository
import com.twogether.app.domain.repository.RitualRepository
import com.twogether.app.domain.usecase.MarkStepDone
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

data class TodayUiState(
    val yourName: String? = null,
    val partnerName: String? = "Partner",
    val streakCount: Int = 0,
    val yourCompletions: StepCompletion? = null,
    val partnerCompletions: StepCompletion? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

@Singleton
class TodayViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val pairingRepository: PairingRepository,
    private val ritualRepository: RitualRepository,
    private val markStepDone: MarkStepDone,
) : ViewModel() {

    var uiState by mutableStateOf(TodayUiState())
        private set

    private var currentUid: String? = null
    private var coupleId: String? = null
    private var partnerUid: String? = null
    private var dayTimezone: String? = null

    init {
        viewModelScope.launch {
            authRepository.currentUser.collect { user ->
                currentUid = user?.uid
                refresh()
            }
        }

        viewModelScope.launch {
            pairingRepository.couple.collect { couple ->
                coupleId = couple?.coupleId
                dayTimezone = couple?.dayTimezone
                partnerUid = couple?.memberIds?.filter { it != currentUid }?.firstOrNull()
                refresh()
            }
        }
    }

    private fun refresh() {
        val cid = coupleId
        val uid = currentUid
        val puid = partnerUid
        val tz = dayTimezone
        if (cid == null || uid == null || puid == null || tz == null) return

        val dayKey = dayKey(tz)

        viewModelScope.launch {
            ritualRepository.observeMyCompletions(cid, uid, dayKey).collect { myComp ->
                uiState = uiState.copy(yourCompletions = myComp)
            }
        }

        viewModelScope.launch {
            ritualRepository.observePartnerCompletions(cid, puid, dayKey).collect { partnerComp ->
                uiState = uiState.copy(partnerCompletions = partnerComp)
            }
        }
    }

    fun onStepDone(step: RitualStep) {
        val cid = coupleId
        val tz = dayTimezone
        if (cid == null || tz == null) return
        val dayKey = dayKey(tz)

        viewModelScope.launch {
            val result = markStepDone(cid, dayKey, step)
            when (result) {
                is Result.Error -> uiState = uiState.copy(error = result.exception.message ?: "Failed")
                is Result.Success -> { /* flow updates uiState via observers */ }
            }
        }
    }

    private fun dayKey(timezone: String): String {
        return ZonedDateTime.now(ZoneId.of(timezone))
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }
}
