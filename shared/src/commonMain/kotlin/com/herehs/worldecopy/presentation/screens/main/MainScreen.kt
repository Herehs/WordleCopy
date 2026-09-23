package com.herehs.worldecopy.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.herehs.worldecopy.Res
import com.herehs.worldecopy.arrow_back_up
import com.herehs.worldecopy.presentation.components.RoundedButton
import com.herehs.worldecopy.presentation.screens.main.components.Keyboard
import com.herehs.worldecopy.presentation.screens.main.components.WordGrid
import com.herehs.worldecopy.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun MainScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = koinViewModel(),
    toLeaderBoard: () -> Unit = {}
){
    val state by viewModel.screenState.collectAsState()
    MainScreen(
        modifier = modifier,
        onButtonClick = viewModel::onLetterInput,
        onClearClick = viewModel::onBackspace,
        onSubmitClick = viewModel::onSubmit,
        toLeaderBoard = toLeaderBoard,
        state = state
    )
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
    ) {
        RoundedButton(
            modifier = Modifier
                .fillMaxWidth(.2f)
                .padding(5.dp),
            color = MaterialTheme.colorScheme.outline,
            onClick = toLeaderBoard,
            contentAlignment = Alignment.Center
        ){
            Icon(
                painter = painterResource(Res.drawable.arrow_back_up),
                contentDescription = ""
            )
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
