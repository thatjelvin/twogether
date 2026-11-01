package com.twogether.app.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.twogether.app.core.result.Result
import com.twogether.app.domain.model.Couple
import com.twogether.app.data.datasource.PairingDataSource
import com.twogether.app.domain.repository.PairingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PairingRepositoryImpl @Inject constructor(
    private val dataSource: PairingDataSource,
    private val auth: FirebaseAuth,
) : PairingRepository {

    private val _couple = MutableStateFlow<Couple?>(null)
    override val couple: Flow<Couple?> = _couple.asStateFlow()

    override suspend fun createCouple(): Result<Couple> {
        return withContext(Dispatchers.IO) {
            try {
                val uid = auth.currentUser?.uid
                    ?: throw IllegalStateException("Not signed in")
                val inviteCode = InviteCode.generate()
                val couple = dataSource.createCouple(uid, inviteCode)
                _couple.value = couple
                Result.Success(couple)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun joinCouple(inviteCode: String): Result<Couple> {
        return withContext(Dispatchers.IO) {
            try {
                val uid = auth.currentUser?.uid
                    ?: throw IllegalStateException("Not signed in")
                val couple = dataSource.joinCouple(uid, inviteCode)
                _couple.value = couple
                Result.Success(couple)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override fun isPaired(): Boolean = _couple.value != null

    private object InviteCode {
        private const val CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789"
        fun generate(): String = (1..6).map { CHARS.random() }.joinToString("")
    }
}
