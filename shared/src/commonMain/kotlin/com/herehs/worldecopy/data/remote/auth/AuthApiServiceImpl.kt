package com.herehs.worldecopy.data.remote.auth

import com.herehs.worldecopy.data.remote.auth.dto.LoginRequestDto
import com.herehs.worldecopy.data.remote.auth.dto.LoginResponseDto
import com.herehs.worldecopy.data.remote.auth.dto.RegisterRequestDto
import com.herehs.worldecopy.data.remote.auth.dto.RegisterResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthApiServiceImpl(
    private val client: HttpClient
) : AuthApiService {
    override suspend fun register(
        username: String,
        password: String
    ): RegisterResponseDto {
        return client.post("/api/players/register") {
            setBody(
                LoginRequestDto(
                    username = username,
                    password = password
                )
            )
        }.body()
    }

    override suspend fun login(
        username: String,
        password: String
    ): LoginResponseDto {
        return client.post("/api/players/login") {
            setBody(
                RegisterRequestDto(
                    username = username,
                    password = password
                )
            )
        }.body()
    }
}