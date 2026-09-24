package com.herehs.worldecopy.data.repository

import com.herehs.worldecopy.core.util.Resource
import com.herehs.worldecopy.core.util.safeApiCall
import com.herehs.worldecopy.data.local.token_storage.TokenStorage
import com.herehs.worldecopy.data.local.token_storage.dto.WebToken
import com.herehs.worldecopy.data.remote.auth.AuthApiService
import com.herehs.worldecopy.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val api: AuthApiService,
    private val tokenStorage: TokenStorage,
) : AuthRepository {
    override fun login(
        username: String,
        password: String
    ): Flow<Resource<Unit>> = safeApiCall {
        val response = api.login(username = username, password = password)
        tokenStorage.save(
            WebToken(
                access = response.accessToken,
                refresh = response.refreshToken
            )
        )
    }

    override fun register(
        username: String,
        password: String
    ): Flow<Resource<Unit>> = safeApiCall {
        val response = api.register(username = username, password = password)
        tokenStorage.save(
            WebToken(
                access = response.accessToken,
                refresh = response.refreshToken
            )
        )
    }
}