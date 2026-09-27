package com.herehs.worldecopy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.rememberViewModelStoreOwner
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.herehs.worldecopy.presentation.navigation.Screen
import com.herehs.worldecopy.presentation.screens.authorisation.AuthorisationScreenRoute
import com.herehs.worldecopy.presentation.screens.leaderboard.LeaderBoardRoute
import com.herehs.worldecopy.presentation.screens.main.MainScreenRoute
import com.herehs.worldecopy.presentation.screens.main.MainScreenViewModel
import com.herehs.worldecopy.presentation.screens.registration.RegistrationScreenRoute
import com.herehs.worldecopy.presentation.theme.AppTheme
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun App() {
    AppTheme {
        val appViewModel: AppViewModel = koinViewModel()
        val authState by appViewModel.authState.collectAsStateWithLifecycle()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        )

        when(authState) {
            AuthState.Authenticated -> {
                NavRoot(Screen.Leaderboard)
            }
            AuthState.Loading -> {

            }
            AuthState.Unauthenticated -> {
                NavRoot(Screen.Registration)
            }
        }
    }
}

@Composable
fun NavRoot(
    startScreen: Screen
){
    val backStack = rememberNavBackStack(navConfig, startScreen)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Screen.Registration> {
                RegistrationScreenRoute(
                    modifier = Modifier.systemBarsPadding(),
                    onSignUpClick = {
                        backStack.add(Screen.Leaderboard)
                        backStack.removeAll {
                            it != Screen.Leaderboard
                        }
                    },
                    onHaveAccountClick = {
                        backStack.add(Screen.Authorisation)
                        backStack.removeAll {
                            it != Screen.Authorisation
                        }
                    }
                )
            }
            entry<Screen.Authorisation> {
                AuthorisationScreenRoute(
                    modifier = Modifier.systemBarsPadding(),
                    onSignInClick = {
                        backStack.add(Screen.Leaderboard)
                        backStack.removeAll {
                            it != Screen.Leaderboard
                        }
                    },
                    onDontHaveAccountClick = {
                        backStack.add(Screen.Registration)
                        backStack.removeAll {
                            it != Screen.Registration
                        }
                    }
                )
            }
            entry<Screen.Leaderboard> {
                LeaderBoardRoute(
                    modifier = Modifier.systemBarsPadding(),
                    toMainScreen = {
                        val below = backStack.getOrNull(backStack.lastIndex - 1)
                        if (below is Screen.Main) {
                            backStack.removeLastOrNull()
                        } else {
                            backStack.removeLastOrNull()
                            backStack.add(Screen.Main)
                        }
                    }
                )
            }
            entry<Screen.Main> {
                val mainViewModel: MainScreenViewModel = koinViewModel()
                MainScreenRoute(
                    modifier = Modifier.systemBarsPadding(),
                    viewModel = mainViewModel,
                    toLeaderBoard = {
                        backStack.add(Screen.Leaderboard)
                    },
                    onFinishClick = {
                        backStack.removeAll { it is Screen.Main }
                        backStack.add(Screen.Leaderboard)
                    }
                )
            }
        }
    )

}

private val navConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Screen.Main::class, Screen.Main.serializer())
            subclass(Screen.Leaderboard::class, Screen.Leaderboard.serializer())
            subclass(Screen.Registration::class, Screen.Registration.serializer())
            subclass(Screen.Authorisation::class, Screen.Authorisation.serializer())
        }
    }
}
