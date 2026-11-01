package com.twogether.app.domain.usecase

import com.twogether.app.core.result.Result
import com.twogether.app.domain.model.RitualStep
import com.twogether.app.domain.repository.RitualRepository
import javax.inject.Inject

class MarkStepDone @Inject constructor(
    private val ritualRepository: RitualRepository,
) {
    suspend operator fun invoke(
        coupleId: String,
        dayKey: String,
        step: RitualStep,
    ): Result<Unit> = ritualRepository.markStepDone(coupleId, dayKey, step)
}
