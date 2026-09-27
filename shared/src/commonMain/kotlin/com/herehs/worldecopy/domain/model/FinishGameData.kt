package com.herehs.worldecopy.domain.model

data class FinishGameData(
    val id: Int,
    val won: Boolean,
    val attemptsUsed: Int
)
