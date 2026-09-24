package com.herehs.worldecopy.data.remote.auth

import com.herehs.worldecopy.data.remote.auth.dto.LoginResponseDto
import com.herehs.worldecopy.data.remote.auth.dto.RegisterResponseDto

interface AuthApiService {
    suspend fun register(username: String, password: String): RegisterResponseDto
    suspend fun login(username: String, password: String): LoginResponseDto
}