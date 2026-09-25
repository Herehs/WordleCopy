package com.herehs.worldecopy.data.repository

import com.herehs.worldecopy.core.util.Resource
import com.herehs.worldecopy.core.util.safeApiCall
import com.herehs.worldecopy.data.local.token_storage.TokenStorage
import com.herehs.worldecopy.data.local.token_storage.dto.WebToken
import com.herehs.worldecopy.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class TokenRepositoryImpl(
    private val storage: TokenStorage
) : TokenRepository {
    override suspend fun get(): Flow<WebToken?> = storage.get()
}