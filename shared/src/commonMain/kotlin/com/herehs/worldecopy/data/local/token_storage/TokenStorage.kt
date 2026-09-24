package com.herehs.worldecopy.data.local.token_storage

import com.herehs.worldecopy.data.local.token_storage.dto.WebToken
import kotlinx.coroutines.flow.Flow

interface TokenStorage {
    suspend fun save(token: WebToken)
    suspend fun get(): Flow<WebToken?>

    suspend fun clear()
}

