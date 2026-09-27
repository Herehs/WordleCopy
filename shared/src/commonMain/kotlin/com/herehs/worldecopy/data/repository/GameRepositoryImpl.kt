package com.herehs.worldecopy.data.repository

import com.herehs.worldecopy.core.util.Resource
import com.herehs.worldecopy.core.util.safeApiCall
import com.herehs.worldecopy.data.remote.game.GameApiService
import com.herehs.worldecopy.data.remote.game.mapper.toData
import com.herehs.worldecopy.data.remote.game.mapper.toDomain
import com.herehs.worldecopy.domain.model.GameData
import com.herehs.worldecopy.domain.model.FinishGameData
import com.herehs.worldecopy.domain.model.GameResult
import com.herehs.worldecopy.domain.model.LeaderboardItem
import com.herehs.worldecopy.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow

class GameRepositoryImpl(
    private val api: GameApiService
): GameRepository {
    override fun getLeaderboard(limit: Int): Flow<Resource<List<LeaderboardItem>>> = safeApiCall {
        api.getLeaderboard(limit = limit).toDomain()
    }

    override fun startGame(): Flow<Resource<GameData>> = safeApiCall {
        api.startGame().toDomain()
    }

    override fun finishGame(finishGameData: FinishGameData): Flow<Resource<GameResult>> = safeApiCall {
        api.finishGame(
            id = finishGameData.id,
            gameResult = finishGameData.toData()
        ).toDomain()
    }
}