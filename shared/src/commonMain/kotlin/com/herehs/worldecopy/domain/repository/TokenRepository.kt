package com.herehs.worldecopy.domain.repository

import com.herehs.worldecopy.core.util.Resource
import com.herehs.worldecopy.data.local.token_storage.dto.WebToken
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun get(): Flow<WebToken?>
}