package com.herehs.worldecopy.data.remote.game.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LeaderboardItemDto(
    val rank: Int,
    val username: String,
    @SerialName("total_points")val totalPoints: Int
)