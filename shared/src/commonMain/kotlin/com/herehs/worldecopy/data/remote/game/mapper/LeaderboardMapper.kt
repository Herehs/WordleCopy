package com.herehs.worldecopy.data.remote.game.mapper

import com.herehs.worldecopy.data.remote.game.dto.LeaderboardItemDto
import com.herehs.worldecopy.domain.model.LeaderboardItem

fun List<LeaderboardItemDto>.toDomain(): List<LeaderboardItem> = this.map { data ->
    LeaderboardItem(
        rank = data.rank,
        username = data.username,
        totalPoints = data.totalPoints
    )
}