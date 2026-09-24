package com.herehs.worldecopy.presentation.screens.leaderboard

data class LeaderBoardUiState(
    val leaderboard: List<LeaderBoardComponentState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class LeaderBoardComponentState(
    val name: String,
    val points: Int
)
