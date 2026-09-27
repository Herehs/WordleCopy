package com.herehs.worldecopy.domain.usecase

import com.herehs.worldecopy.core.util.Resource
import com.herehs.worldecopy.domain.model.FinishGameData
import com.herehs.worldecopy.domain.model.GameData
import com.herehs.worldecopy.domain.model.GameResult
import com.herehs.worldecopy.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow

class FinishGameUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke(finishGameData: FinishGameData): Flow<Resource<GameResult>>{
        return gameRepository.finishGame(finishGameData = finishGameData)
    }
}