package com.twogether.app.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.twogether.app.core.result.Result
import com.twogether.app.data.datasource.RitualDataSource
import com.twogether.app.domain.model.RitualStep
import com.twogether.app.domain.model.StepCompletion
import com.twogether.app.domain.repository.RitualRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RitualRepositoryImpl @Inject constructor(
    private val dataSource: RitualDataSource,
    private val auth: FirebaseAuth,
) : RitualRepository {

    override fun observeMyCompletions(coupleId: String, uid: String, dayKey: String): Flow<StepCompletion?> =
        dataSource.observeCompletions(coupleId, dayKey, uid)

    override fun observePartnerCompletions(
        coupleId: String,
        partnerUid: String,
        dayKey: String,
    ): Flow<StepCompletion?> =
        dataSource.observeCompletions(coupleId, dayKey, partnerUid)

    override suspend fun markStepDone(
        coupleId: String,
        dayKey: String,
        step: RitualStep,
    ): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val uid = auth.currentUser?.uid
                    ?: throw IllegalStateException("Not signed in")
                dataSource.markStepDone(coupleId, dayKey, uid, step)
                Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}
