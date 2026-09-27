package com.herehs.worldecopy.data.remote.game

import com.herehs.worldecopy.data.remote.game.dto.FinishGameRequestDto
import com.herehs.worldecopy.data.remote.game.dto.FinishGameResponseDto
import com.herehs.worldecopy.data.remote.game.dto.LeaderboardItemDto
import com.herehs.worldecopy.data.remote.game.dto.StartGameResponseDto
import com.herehs.worldecopy.data.util.throwIfError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody


class GameApiServiceImpl(
    private val client: HttpClient
) : GameApiService {
    override suspend fun getLeaderboard(
        limit: Int
    ): List<LeaderboardItemDto> {
        val response = client.get("/api/players/leaderboard"){
            parameter("limit", limit)
        }

        response.throwIfError()

        return response.body<List<LeaderboardItemDto>>()
    }

    override suspend fun startGame(): StartGameResponseDto {
        val response = client.post("/api/games")

        response.throwIfError()

        return response.body<StartGameResponseDto>()
    }

    override suspend fun finishGame(id: Int, gameResult: FinishGameRequestDto): FinishGameResponseDto {
        val response = client.post("/api/games/$id/finish"){
            setBody(gameResult)
        }
        println("GameApiServiceImpl")
        response.throwIfError()

        return response.body<FinishGameResponseDto>()
    }
}