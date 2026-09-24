package com.herehs.worldecopy.data.util.dto

import com.herehs.worldecopy.data.local.token_storage.dto.WebToken
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenDto(
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: String,
    @SerialName("token_type") val tokenType: String
)


fun RefreshTokenDto.toWebToken() = WebToken(
    access = this.accessToken,
    refresh = this.refreshToken
)