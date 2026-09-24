package com.herehs.worldecopy.data.remote.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDto(
    val username: String,
    val password: String
)
