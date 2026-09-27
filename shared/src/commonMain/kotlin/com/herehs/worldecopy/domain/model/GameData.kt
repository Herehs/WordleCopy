package com.herehs.worldecopy.domain.model

data class GameData(
    val id: Int,
    val answer: String,
    val wordLength: Int,
    val maxAttempts: Int
)
