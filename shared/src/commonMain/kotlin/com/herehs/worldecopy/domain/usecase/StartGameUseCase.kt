package com.herehs.worldecopy.domain.usecase

import com.herehs.worldecopy.core.util.Resource
import com.herehs.worldecopy.domain.model.GameData
import com.herehs.worldecopy.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow

class StartGameUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke(): Flow<Resource<GameData>>{
        return gameRepository.startGame()
    }
}