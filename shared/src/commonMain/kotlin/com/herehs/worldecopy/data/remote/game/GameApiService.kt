package com.herehs.worldecopy.data.remote.game

import com.herehs.worldecopy.data.remote.game.dto.LeaderboardItemDto

interface GameApiService {
    suspend fun getLeaderboard(limit: Int): List<LeaderboardItemDto>
}