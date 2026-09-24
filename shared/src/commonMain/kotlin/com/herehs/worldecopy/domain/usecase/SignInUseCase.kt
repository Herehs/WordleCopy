package com.herehs.worldecopy.domain.usecase

import com.herehs.worldecopy.core.util.Resource
import com.herehs.worldecopy.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class SignInUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        username: String,
        password: String
    ): Flow<Resource<Unit>>{
        return authRepository.register(
            username = username,
            password = password
        )
    }
}