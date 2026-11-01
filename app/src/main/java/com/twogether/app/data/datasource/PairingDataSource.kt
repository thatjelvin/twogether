package com.twogether.app.data.datasource

import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.twogether.app.domain.model.Couple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.flowOn

class PairingDataSource(private val firestore: FirebaseFirestore) {

    suspend fun createCouple(uid: String, inviteCode: String): Couple {
        val coupleId = firestore.collection("couples").document().id
        val now = System.currentTimeMillis()
        val dto = mapOf(
            "memberIds" to listOf(uid),
            "inviteCode" to inviteCode,
            "createdAt" to now,
            "dayTimezone" to null,
        )
        Tasks.await(
            firestore.collection("couples").document(coupleId).set(dto)
        )
        Tasks.await(
            firestore.collection("users").document(uid)
                .set(mapOf("coupleId" to coupleId), SetOptions.merge())
        )
        return Couple(
            coupleId = coupleId,
            memberIds = listOf(uid),
            inviteCode = inviteCode,
            createdAt = now,
            dayTimezone = null,
        )
    }

    suspend fun joinCouple(uid: String, inviteCode: String): Couple {
        val query = firestore.collection("couples")
            .whereEqualTo("inviteCode", inviteCode)
            .limit(1)
        val snapshot = Tasks.await(query.get())
        if (snapshot.isEmpty) {
            throw IllegalArgumentException("Invalid invite code")
        }
        val doc = snapshot.documents.single()
        val coupleId = doc.id
        val memberIds = (doc.get("memberIds") as? List<*>)?.map { it.toString() } ?: emptyList()
        val storedInviteCode = doc.get("inviteCode") as? String
        if (storedInviteCode != inviteCode) {
            throw IllegalArgumentException("Invite code no longer valid")
        }
        if (memberIds.size >= 2) {
            throw IllegalArgumentException("Couple is already full")
        }
        Tasks.await(firestore.runTransaction { transaction ->
            val freshDoc = transaction.get(doc.reference)
            val freshInvite = freshDoc.get("inviteCode") as? String
            val freshMembers = (freshDoc.get("memberIds") as? List<*>)?.map { it.toString() } ?: emptyList()
            if (freshInvite != inviteCode) {
                throw IllegalArgumentException("Invite code no longer valid")
            }
            if (freshMembers.size >= 2) {
                throw IllegalArgumentException("Couple is already full")
            }
            transaction.update(doc.reference, "memberIds", freshMembers + uid)
            transaction.update(doc.reference, "inviteCode", null)
            transaction.update(
                firestore.collection("users").document(uid),
                "coupleId",
                coupleId,
            )
            null
        })
        return Couple(
            coupleId = coupleId,
            memberIds = memberIds + uid,
            inviteCode = null,
            createdAt = doc.get("createdAt") as? Long ?: 0,
            dayTimezone = doc.get("dayTimezone") as? String,
        )
    }

    fun observeCouple(coupleId: String): Flow<Couple?> = callbackFlow {
        val registration = firestore.collection("couples").document(coupleId)
            .addSnapshotListener { snapshot, _ ->
                val data = snapshot?.data ?: return@addSnapshotListener
                val memberIds = (data["memberIds"] as? List<*>)?.map { it.toString() } ?: emptyList()
                trySend(
                    Couple(
                        coupleId = coupleId,
                        memberIds = memberIds,
                        inviteCode = data["inviteCode"] as? String,
                        createdAt = data["createdAt"] as? Long ?: 0,
                        dayTimezone = data["dayTimezone"] as? String,
                    )
                )
            }
        awaitClose { registration.remove() }
    }.flowOn(Dispatchers.IO)
}
