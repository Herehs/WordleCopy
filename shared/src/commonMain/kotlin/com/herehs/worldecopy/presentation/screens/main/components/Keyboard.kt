package com.herehs.worldecopy.presentation.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.herehs.worldecopy.presentation.theme.AppTheme
import com.herehs.worldecopy.presentation.theme.DarkWarning
import com.herehs.worldecopy.presentation.theme.golosFontFamily

@Composable
fun Keyboard(
    keyStates: Map<Char, LetterState> = emptyMap(),
    onButtonClick: (Char) -> Unit = {},
    onClearClick: () -> Unit = {},
    onSubmitClick: () -> Unit = {},
){
    val rows = listOf(
        "йцукенгшщзхъ",
        "фывапролджэ",
        "ячсмитьбю"
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        rows.forEachIndexed { index, row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                if (index == 2){
                    Key(
                        letter = "del",
                        modifier = Modifier
                            .weight(2f)
                            .height(40.dp),
                        fontSize = 12.sp,
                        onClick = onClearClick
                    )
                }
                row.forEach { char ->
                    Key(
                        letter = char.toString(),
                        modifier = Modifier
                            .height(40.dp)
                            .aspectRatio(27f/40f),
                        onClick = { onButtonClick(char) },
                        state = keyStates[char] ?: LetterState.DEFAULT
                    )
                }
                if (index == 2){
                    Key(
                        letter = "enter",
                        modifier = Modifier
                            .weight(2f)
                            .height(40.dp),
                        fontSize = 12.sp,
                        onClick = onSubmitClick
                    )
                }
            }
        }
    }
}

@Composable
private fun Key(
    letter: String = "",
    state: LetterState = LetterState.DEFAULT,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 24.sp,
    onClick: () -> Unit = {}
){

    val color = when(state) {
        LetterState.DEFAULT -> MaterialTheme.colorScheme.outline
        LetterState.CORRECT -> MaterialTheme.colorScheme.tertiary
        LetterState.PRESENT -> DarkWarning
        LetterState.ABSENT -> MaterialTheme.colorScheme.surface
    }
    Box(
        modifier = modifier
            .background(
                color = color,
                shape = RoundedCornerShape(5.dp)
            )
            .clickable(
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = letter,
            fontSize = fontSize,
            fontFamily = golosFontFamily(),
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
private fun KeyPreview(){
    AppTheme {
        var text by remember { mutableStateOf("") }
        Scaffold(

        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Bottom
            ){
                Text(
                    text = text
                )
                Keyboard(
                    onButtonClick = { text += it },
                    onClearClick = { text = text.dropLast(1) }
                )

            }
        }
    }
}