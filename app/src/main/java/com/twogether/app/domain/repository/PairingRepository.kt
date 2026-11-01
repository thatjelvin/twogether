package com.twogether.app.domain.repository

import com.twogether.app.core.result.Result
import com.twogether.app.domain.model.Couple
import kotlinx.coroutines.flow.Flow

interface PairingRepository {
    suspend fun createCouple(): Result<Couple>
    suspend fun joinCouple(inviteCode: String): Result<Couple>
    val couple: Flow<Couple?>
    fun isPaired(): Boolean
}
