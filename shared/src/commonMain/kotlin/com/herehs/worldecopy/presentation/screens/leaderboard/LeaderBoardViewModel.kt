package com.herehs.worldecopy.presentation.screens.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herehs.worldecopy.core.util.Resource
import com.herehs.worldecopy.domain.model.LeaderboardItem
import com.herehs.worldecopy.domain.usecase.GetLeaderboardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LeaderBoardViewModel(
    private val leaderboardUseCase: GetLeaderboardUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LeaderBoardUiState())
    val state = _state.asStateFlow()

    fun updateLeaderboard(){
        viewModelScope.launch {
            leaderboardUseCase(100).collect { result ->
                when(result) {
                    is Resource.Error<List<LeaderboardItem>> -> {
                        _state.update { state ->
                            state.copy(isLoading = false, error = result.message)
                        }
                    }
                    is Resource.Loading<List<LeaderboardItem>> -> {
                        _state.update { state ->
                            state.copy(isLoading = true, error = null)
                        }
                    }
                    is Resource.Success<List<LeaderboardItem>> -> {
                        _state.update { state ->
                            state.copy(
                                isLoading = false,
                                error = null,
                                leaderboard = result.data?.toUiState() ?: emptyList()
                            )
                        }
                    }
                }
            }
        }

    }


    init {
        updateLeaderboard()
    }
}