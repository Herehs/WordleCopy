package com.herehs.worldecopy.presentation.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.LoadingIndicatorDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.herehs.worldecopy.Res
import com.herehs.worldecopy.arrow_back_up
import com.herehs.worldecopy.presentation.components.RoundedButton
import com.herehs.worldecopy.presentation.screens.authorisation.AuthorisationViewModel
import com.herehs.worldecopy.presentation.screens.main.components.FinishGameDialog
import com.herehs.worldecopy.presentation.screens.main.components.Keyboard
import com.herehs.worldecopy.presentation.screens.main.components.WordGrid
import com.herehs.worldecopy.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import kotlin.let


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MainScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = koinViewModel(),
    toLeaderBoard: () -> Unit = {},
    onFinishClick: () -> Unit
){
    val state by viewModel.state.collectAsState()

    var dialogEvent by remember { mutableStateOf<MainScreenViewModel.GameEvent?>(null) }

    LaunchedEffect(Unit){
        viewModel.events.collect { event ->
            dialogEvent = event
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ){
        AnimatedVisibility(
            visible = state.isLoading,
            enter = fadeIn(
                tween(400)
            ),
            exit = fadeOut(tween(400))
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                LoadingIndicator(
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }

        AnimatedVisibility(
            visible = !state.isLoading,
            enter = fadeIn(
                tween(400)
            ),
            exit = fadeOut(tween(400))
        ){
            MainScreen(
                modifier = Modifier,
                onButtonClick = viewModel::onLetterInput,
                onClearClick = viewModel::onBackspace,
                onSubmitClick = viewModel::onSubmit,
                toLeaderBoard = toLeaderBoard,
                state = state
            )
        }

        val visible = dialogEvent != null

        var lastEvent by remember { mutableStateOf<MainScreenViewModel.GameEvent?>(null) }
        LaunchedEffect(dialogEvent) {
            dialogEvent?.let { lastEvent = it }
        }

        AnimatedVisibility(
            visible,
            enter = fadeIn(
                tween(400)
            ),
            exit = fadeOut(tween(400))
        ){
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = .5f))
                    .fillMaxSize(),
            )
        }
        AnimatedVisibility(
            visible, enter = fadeIn(
                tween(400)
            ) + scaleIn(
                animationSpec = tween(400),
                initialScale = 0.8f
            ),
            exit = fadeOut(tween(400))
        ){
            lastEvent?.let { event ->
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    FinishGameDialog(
                        modifier = Modifier.align(
                            Alignment.Center
                        ),
                        onClick = onFinishClick,
                        event = event
                    )
                }
            }
        }

    }
}
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    state: MainScreenUiState = MainScreenUiState(),
    onButtonClick: (Char) -> Unit = {},
    onClearClick: () -> Unit = {},
    onSubmitClick: () -> Unit = {},
    toLeaderBoard: () -> Unit = {}
){
    Column(
        modifier = modifier
            .widthIn(max = 411.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            RoundedButton(
                modifier = Modifier
                    .fillMaxWidth(.2f)
                    .padding(5.dp),
                color = MaterialTheme.colorScheme.outline,
                onClick = toLeaderBoard,
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.arrow_back_up),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Text(text = state.gridState.target, fontSize = 20.sp)
        }
        WordGrid(
            state = state.gridState
        )
        Spacer(
            modifier = Modifier.fillMaxHeight(.2f)
        )
        Keyboard(
            onButtonClick = onButtonClick,
            onClearClick = onClearClick,
            onSubmitClick = onSubmitClick
        )
    }
}


@Preview
@Composable
fun MainScreenTest(){

    AppTheme {
        Scaffold { paddingValues ->
            var state by remember { mutableStateOf(MainScreenUiState()) }
            MainScreen(
                modifier = Modifier.padding(paddingValues),
                state = state,
                onButtonClick = {
                    state = state.copy(
                        gridState = state.gridState.onLetterInput(it)
                    )
                },
                onClearClick = {
                    state = state.copy(
                        gridState = state.gridState.onBackspace()
                    )

                },
                onSubmitClick = {
                    state = state.copy(
                        gridState = state.gridState.onSubmit()
                    )
                }
            )
        }
    }
}
