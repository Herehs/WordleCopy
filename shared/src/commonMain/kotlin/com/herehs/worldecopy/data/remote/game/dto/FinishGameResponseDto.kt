package com.herehs.worldecopy.data.remote.game.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FinishGameResponseDto(
    val id: Int,
    val status: String,
    val answer: String,
    @SerialName("attempts_used") val attemptsUsed: Int,
    @SerialName("max_attempts") val maxAttempts: Int,
    val score: Int,
    @SerialName("started_at") val startedAt: String,
    @SerialName("finished_at") val finishedAt: String
)
