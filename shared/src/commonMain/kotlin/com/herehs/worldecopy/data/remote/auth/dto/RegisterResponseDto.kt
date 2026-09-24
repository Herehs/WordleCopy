package com.herehs.worldecopy.data.remote.auth.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
    @SerialName("id") val id: Int,
    @SerialName("username") val username: String,
    @SerialName("total_points") val points: Int
)
