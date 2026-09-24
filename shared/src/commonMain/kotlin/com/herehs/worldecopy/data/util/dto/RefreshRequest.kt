package com.herehs.worldecopy.data.util.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshRequest(
    @SerialName("refresh_token")
    val token: String = ""
)
