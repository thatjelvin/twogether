package com.twogether.app.domain.repository

import com.twogether.app.core.result.Result
import com.twogether.app.domain.model.RitualStep
import com.twogether.app.domain.model.StepCompletion
import kotlinx.coroutines.flow.Flow

interface RitualRepository {
    fun observeMyCompletions(coupleId: String, uid: String, dayKey: String): Flow<StepCompletion?>
    fun observePartnerCompletions(coupleId: String, partnerUid: String, dayKey: String): Flow<StepCompletion?>
    suspend fun markStepDone(coupleId: String, dayKey: String, step: RitualStep): Result<Unit>
}
