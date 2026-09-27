package com.herehs.worldecopy.data.remote.game

import com.herehs.worldecopy.data.remote.game.dto.FinishGameRequestDto
import com.herehs.worldecopy.data.remote.game.dto.FinishGameResponseDto
import com.herehs.worldecopy.data.remote.game.dto.LeaderboardItemDto
import com.herehs.worldecopy.data.remote.game.dto.StartGameResponseDto

interface GameApiService {
    suspend fun getLeaderboard(limit: Int): List<LeaderboardItemDto>

    suspend fun startGame(): StartGameResponseDto

    suspend fun finishGame(id: Int, gameResult: FinishGameRequestDto): FinishGameResponseDto
}