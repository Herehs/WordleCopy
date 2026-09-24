package com.herehs.worldecopy.data.remote.auth.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class UnprocessableEntityDto(
    val detail: List<Detail>
)

@Serializable
data class Detail(
    val loc: List<JsonElement> = emptyList(),
    val msg: String,
    val type: String? = null
)