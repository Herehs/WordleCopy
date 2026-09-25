package com.herehs.worldecopy.presentation.screens.leaderboard

import androidx.compose.material3.FabPosition
import com.herehs.worldecopy.domain.model.LeaderboardItem

data class LeaderBoardUiState(
    val leaderboard: List<LeaderBoardComponentState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class LeaderBoardComponentState(
    val name: String,
    val points: Int
)

fun List<LeaderboardItem>.toUiState(): List<LeaderBoardComponentState> {
    val sorted = this.sortedBy { it.rank }

    return sorted.map { item ->
        LeaderBoardComponentState(
            name = item.username,
            points = item.totalPoints
        )
    }
}