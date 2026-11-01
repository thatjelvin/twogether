package com.twogether.app.domain.usecase

import com.twogether.app.core.result.Result
import com.twogether.app.domain.model.Couple
import com.twogether.app.domain.repository.PairingRepository
import javax.inject.Inject

class CreateCouple @Inject constructor(
    private val pairingRepository: PairingRepository,
) {
    suspend operator fun invoke(): Result<Couple> = pairingRepository.createCouple()
}
