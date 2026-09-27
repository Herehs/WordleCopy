package com.herehs.worldecopy.data.remote.game.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FinishGameRequestDto(
    val won: Boolean,
    @SerialName("attempts_used") val attemptsUsed: Int
)