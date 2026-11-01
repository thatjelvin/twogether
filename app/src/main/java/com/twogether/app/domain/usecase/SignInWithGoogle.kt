package com.twogether.app.domain.usecase

import com.twogether.app.core.result.Result
import com.twogether.app.domain.model.User
import com.twogether.app.domain.repository.AuthRepository
import javax.inject.Inject

class SignInWithGoogle @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(idToken: String): Result<User> = authRepository.signInWithGoogle(idToken)
}
