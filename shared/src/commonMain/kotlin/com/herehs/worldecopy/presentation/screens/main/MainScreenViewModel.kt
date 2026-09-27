package com.herehs.worldecopy.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herehs.worldecopy.core.util.Resource
import com.herehs.worldecopy.domain.model.FinishGameData
import com.herehs.worldecopy.domain.model.GameData
import com.herehs.worldecopy.domain.usecase.FinishGameUseCase
import com.herehs.worldecopy.domain.usecase.StartGameUseCase
import com.herehs.worldecopy.presentation.screens.main.components.WordGridState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val WORD_LENGTH = 5


class MainScreenViewModel(
    private val startGameUseCase: StartGameUseCase,
    private val finishGameUseCase: FinishGameUseCase
) : ViewModel() {

    private val _eventChannel = Channel<GameEvent>(Channel.BUFFERED)
    val events = _eventChannel.receiveAsFlow()

    sealed interface GameEvent {
        data object Won : GameEvent
        data object Lost : GameEvent
    }

    private val _state = MutableStateFlow(MainScreenUiState())
    val state = _state.asStateFlow()

    fun onLetterInput(char: Char){
        if (_state.value.ended) return
        _state.update { state ->
            state.copy(
                gridState = state.gridState.onLetterInput(char = char)
            )
        }
    }
    fun onBackspace(){
        _state.update { state ->
            state.copy(
                gridState = state.gridState.onBackspace()
            )
        }
    }
    fun onSubmit() {
        // checks is user won
        val current = _state.value
        val newGridState = current.gridState.onSubmit()

        if (newGridState === current.gridState) return

        val won = newGridState.stack.last().lowercase() == newGridState.target.lowercase()
        val lost = !won && newGridState.stack.size >= 6

        _state.update { it.copy(gridState = newGridState, won = won) }

        if (won || lost) {
            _state.update { it.copy(ended = true) }
            finishGame(gameId = current.gameId, won = won, attempts = newGridState.stack.size)
        }
    }

    fun startGame(){
        viewModelScope.launch {
            startGameUseCase().collect { result ->
                when(result) {
                    is Resource.Error<GameData> -> {
                        _state.update { state ->
                            state.copy(isLoading = false, error = result.message)
                        }
                    }
                    is Resource.Loading<GameData> -> {
                        _state.update { state ->
                            state.copy(isLoading = true, error = null)
                        }
                    }
                    is Resource.Success<GameData> -> {
                        _state.update { state ->
                            state.copy(
                                gameId = result.data?.id ?: 0,
                                isLoading = false,
                                error = null,
                                gridState = WordGridState(
                                    target = result.data?.answer ?: ""
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun finishGame(gameId: Int, won: Boolean, attempts: Int) {
        viewModelScope.launch {
            finishGameUseCase(
                finishGameData = FinishGameData(
                    id = gameId,
                    won = won,
                    attemptsUsed = attempts
                )
            ).collect { result ->
                when (result) {
                    is Resource.Error -> _state.update { it.copy(error = result.message) }
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                score = result.data?.score ?: 0
                            )
                        }
                        _eventChannel.send(if (won) GameEvent.Won else GameEvent.Lost)
                    }
                }
            }
        }
    }

    init {
        startGame()
    }
}


fun WordGridState.onLetterInput(char: Char): WordGridState {
    if (currentWord.length >= WORD_LENGTH) return this
    return copy(currentWord = currentWord + char)
}

fun WordGridState.onBackspace(): WordGridState {
    if (currentWord.isEmpty()) return this
    return copy(currentWord = currentWord.dropLast(1))
}

fun WordGridState.onSubmit(): WordGridState {
    if (currentWord.length != WORD_LENGTH) return this
    return copy(
        stack = stack + currentWord,
        currentWord = ""
    )
}
