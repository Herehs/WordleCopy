package com.herehs.worldecopy.domain.repository

import com.herehs.worldecopy.core.util.Resource
import com.herehs.worldecopy.domain.model.LeaderboardItem
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getLeaderboard(limit: Int): Flow<Resource<List<LeaderboardItem>>>
}