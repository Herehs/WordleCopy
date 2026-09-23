package com.herehs.worldecopy.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.herehs.worldecopy.presentation.screens.main.components.WordGridState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val WORD_LENGTH = 5


class MainScreenViewModel : ViewModel() {
    private val _screenState = MutableStateFlow(MainScreenUiState())
    val screenState = _screenState.asStateFlow()

    fun onLetterInput(char: Char){
        _screenState.update { state ->
            state.copy(
                gridState = state.gridState.onLetterInput(char = char)
            )
        }
    }
    fun onBackspace(){
        _screenState.update { state ->
            state.copy(
                gridState = state.gridState.onBackspace()
            )
        }
    }
    fun onSubmit() {
        _screenState.update { state ->
            state.copy(
                gridState = state.gridState.onSubmit()
            )
        }
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
