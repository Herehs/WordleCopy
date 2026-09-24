package com.herehs.worldecopy.presentation.screens.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.herehs.worldecopy.Res
import com.herehs.worldecopy.presentation.components.RoundedButton
import com.herehs.worldecopy.presentation.theme.AppTheme
import com.herehs.worldecopy.presentation.theme.golosFontFamily
import com.herehs.worldecopy.start_game
import org.jetbrains.compose.resources.stringResource


@Composable
fun LeaderBoardRoute(
    modifier: Modifier = Modifier,
    toMainScreen: () -> Unit = {}
){
    LeaderboardScreen(
        modifier = modifier,
        toMainScreen = toMainScreen
    )
}

@Composable
fun LeaderboardScreen(
    modifier: Modifier = Modifier,
    state: LeaderBoardUiState = LeaderBoardUiState(),
    toMainScreen: () -> Unit = {}
){

    Box(
        modifier = modifier
            .fillMaxSize()
    ){
        LazyColumn(
            modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .padding(horizontal = 10.dp)
        ) {
            itemsIndexed(state.leaderboard){ index, item ->
                Row(
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(
                            color = MaterialTheme.colorScheme.surface
                        )
                        .height(56.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$index. ${item.name}",
                        fontSize = 18.sp,
                        fontFamily = golosFontFamily(),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "${item.points}",
                        fontSize = 18.sp,
                        fontFamily = golosFontFamily(),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                )
                .background(
                    color = MaterialTheme.colorScheme.surface
                )
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            RoundedButton(
                modifier = Modifier
                    .padding(10.dp),
                contentAlignment = Alignment.Center,
                color = MaterialTheme.colorScheme.tertiary,
                onClick = toMainScreen
            ) {
                Text(
                    text = stringResource(Res.string.start_game),
                    fontSize = 18.sp,
                    fontFamily = golosFontFamily(),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}


@Preview
@Composable
fun LeaderboardScreenPreview(){
    AppTheme {
        val leaderboard = mutableListOf<LeaderBoardComponentState>()
        repeat(10){
            leaderboard.add(
                LeaderBoardComponentState("asd", 100)
            )
        }
        Scaffold { paddingValues ->
            LeaderboardScreen(
                modifier = Modifier.padding(paddingValues),
                state = LeaderBoardUiState(leaderboard = leaderboard)
            )
        }
    }
}