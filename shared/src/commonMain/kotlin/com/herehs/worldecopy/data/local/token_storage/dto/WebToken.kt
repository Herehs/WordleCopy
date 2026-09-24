package com.herehs.worldecopy.data.local.token_storage.dto

import kotlinx.serialization.Serializable

@Serializable
data class WebToken(
    val access: String,
    val refresh: String
)
