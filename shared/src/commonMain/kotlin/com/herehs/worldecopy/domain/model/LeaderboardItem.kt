package com.herehs.worldecopy.domain.model

data class LeaderboardItem(
    val rank: Int = 0,
    val username: String = "",
    val totalPoints: Int = 0
)