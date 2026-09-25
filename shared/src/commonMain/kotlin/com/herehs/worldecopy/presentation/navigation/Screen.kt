package com.herehs.worldecopy.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen : NavKey {
    @Serializable data object Main : Screen
    @Serializable data object Leaderboard : Screen
    @Serializable data object Registration : Screen
    @Serializable data object Authorisation : Screen
}