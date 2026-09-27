package com.herehs.worldecopy.data.remote.game.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StartGameResponseDto(
    val id: Int,
    val answer: String,
    @SerialName("word_length") val wordLength: Int,
    @SerialName("max_attempts") val maxAttempts: Int,
    @SerialName("started_at") val startedAt: String
)