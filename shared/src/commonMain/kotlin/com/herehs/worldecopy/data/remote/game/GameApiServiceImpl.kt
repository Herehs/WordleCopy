package com.herehs.worldecopy.data.remote.game

import com.herehs.worldecopy.data.remote.game.dto.LeaderboardItemDto
import com.herehs.worldecopy.data.util.throwIfError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter


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
}