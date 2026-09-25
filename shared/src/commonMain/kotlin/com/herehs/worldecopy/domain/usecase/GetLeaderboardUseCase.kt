package com.herehs.worldecopy.domain.usecase

import com.herehs.worldecopy.core.util.Resource
import com.herehs.worldecopy.domain.model.LeaderboardItem
import com.herehs.worldecopy.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow

data class GetLeaderboardUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke(limit: Int): Flow<Resource<List<LeaderboardItem>>> {
        return gameRepository.getLeaderboard(limit = limit)
    }
}
