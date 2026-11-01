package com.twogether.app.data.datasource

import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.twogether.app.domain.model.RitualStep
import com.twogether.app.domain.model.StepCompletion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class RitualDataSource(private val firestore: FirebaseFirestore) {

    private fun completionsDoc(coupleId: String, dayKey: String, uid: String) =
        firestore.collection("couples")
            .document(coupleId)
            .collection("days")
            .document(dayKey)
            .collection("completions")
            .document(uid)

    fun observeCompletions(coupleId: String, dayKey: String, uid: String): Flow<StepCompletion?> =
        callbackFlow {
            val registration = completionsDoc(coupleId, dayKey, uid)
                .addSnapshotListener { snapshot, _ ->
                    val data = snapshot?.data ?: return@addSnapshotListener
                    trySend(
                        StepCompletion(
                            dayKey = dayKey,
                            reflectionDone = data["reflectionDone"] as? Boolean ?: false,
                            prayerPosted = data["prayerPosted"] as? Boolean ?: false,
                            puzzleDone = data["puzzleDone"] as? Boolean ?: false,
                            completedAt = data["completedAt"] as? Long,
                        )
                    )
                }
            awaitClose { registration.remove() }
        }.flowOn(Dispatchers.IO)

    suspend fun markStepDone(coupleId: String, dayKey: String, uid: String, step: RitualStep) =
        withContext(Dispatchers.IO) {
            Tasks.await(
                completionsDoc(coupleId, dayKey, uid)
                    .set(
                        mapOf(step.fieldName() to true, "completedAt" to System.currentTimeMillis()),
                        SetOptions.merge(),
                    )
            )
        }
}

private fun RitualStep.fieldName(): String = when (this) {
    RitualStep.REFLECTION -> "reflectionDone"
    RitualStep.PRAYER -> "prayerPosted"
    RitualStep.PUZZLE -> "puzzleDone"
}
