package com.herehs.worldecopy.data.repository

import com.herehs.worldecopy.core.util.Resource
import com.herehs.worldecopy.core.util.safeApiCall
import com.herehs.worldecopy.data.remote.auth.AuthApiService
import com.herehs.worldecopy.data.remote.game.GameApiService
import com.herehs.worldecopy.data.remote.game.mapper.toDomain
import com.herehs.worldecopy.domain.model.LeaderboardItem
import com.herehs.worldecopy.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow

class GameRepositoryImpl(
    private val api: GameApiService
): GameRepository {
    override fun getLeaderboard(limit: Int): Flow<Resource<List<LeaderboardItem>>> = safeApiCall {
        api.getLeaderboard(limit = limit).toDomain()
    }
}