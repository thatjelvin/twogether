package com.twogether.app.domain.repository

import com.twogether.app.core.result.Result
import com.twogether.app.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<User>
    fun signOut(): Result<Unit>
    val currentUser: Flow<User?>
}
