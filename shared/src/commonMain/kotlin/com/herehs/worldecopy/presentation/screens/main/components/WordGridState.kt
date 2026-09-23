package com.herehs.worldecopy.presentation.screens.main.components

data class WordGridState(
    val target: String = "",
    val stack: List<String> = emptyList(),
    val currentWord: String = ""
)