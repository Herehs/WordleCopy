package com.herehs.worldecopy.domain.repository

import com.herehs.worldecopy.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(username: String, password: String): Flow<Resource<Unit>>
    fun register(username: String, password: String): Flow<Resource<Unit>>
}