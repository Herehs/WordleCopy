package com.herehs.worldecopy.presentation.screens.main

import com.herehs.worldecopy.presentation.screens.main.components.LetterState
import com.herehs.worldecopy.presentation.screens.main.components.WordGridState

data class MainScreenUiState(
    val keyState: Map<Char, LetterState> = emptyMap(),
    val gridState: WordGridState = WordGridState(),
    val isLoading: Boolean = false,
    val error: String? = null
)