package com.twogether.app.data.repository

import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.twogether.app.core.result.Result
import com.twogether.app.domain.model.User
import com.twogether.app.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthRepository {

    override suspend fun signInWithGoogle(idToken: String): Result<User> {
        return withContext(Dispatchers.IO) {
            try {
                val credential = GoogleAuthProvider.getCredential(idToken, "")
                val result = Tasks.await(auth.signInWithCredential(credential))
                val user = result.user
                    ?: return@withContext Result.Error(IllegalStateException("No user in sign-in result"))
                Result.Success(
                    User(
                        uid = user.uid,
                        displayName = user.displayName,
                        photoUrl = user.photoUrl?.toString(),
                        coupleId = null,
                        timezone = null,
                    )
                )
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override fun signOut(): Result<Unit> {
        return try {
            auth.signOut()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override val currentUser: Flow<User?>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
                trySend(
                    firebaseAuth.currentUser?.let { u ->
                        User(u.uid, u.displayName, u.photoUrl?.toString(), null, null)
                    }
                )
            }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }
}
