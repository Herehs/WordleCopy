package com.herehs.worldecopy.presentation.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.herehs.worldecopy.presentation.theme.AppTheme
import com.herehs.worldecopy.presentation.theme.DarkWarning
import com.herehs.worldecopy.presentation.theme.golosFontFamily


@Composable
fun WordGrid(
    state: WordGridState = WordGridState(),
){
    Column {
        repeat(6){ i ->
            val word = when {
                i < state.stack.size -> state.stack[i]
                i == state.stack.size -> state.currentWord
                else -> ""
            }
            val isSubmitted = i < state.stack.size

            WordRow(
                word = word,
                target = state.target,
                showLetterState = isSubmitted
            )

        }
    }
}
@Composable
fun WordRow(
    word: String = "",
    target: String = "",
    showLetterState: Boolean = false
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        repeat(5){ i ->
            val char = word.getOrNull(i)

            val letterState = if (showLetterState && char != null) {
                calculateLetterState(char, i, word, target)
            } else {
                LetterState.DEFAULT
            }

            val color = if (char == null) {
                MaterialTheme.colorScheme.surface
            } else {
                when (letterState) {
                    LetterState.DEFAULT -> MaterialTheme.colorScheme.outline
                    LetterState.ABSENT -> MaterialTheme.colorScheme.outline
                    LetterState.CORRECT -> MaterialTheme.colorScheme.tertiary
                    LetterState.PRESENT -> DarkWarning
                }
            }
            Letter(
                char = char ?: ' ',
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                color = color
            )
        }
    }
}


private fun calculateLetterState(
    char: Char,
    position: Int,
    word: String,
    target: String
): LetterState {
    if (position >= target.length) return LetterState.ABSENT
    if (target[position] == char) return LetterState.CORRECT

    val targetCount = target.count { it == char }
    var usedCount = 0
    for (j in word.indices) {
        if (j == position) continue
        if (word[j] == char) {
            if (target.getOrNull(j) == char) {
                usedCount++
            } else if (j < position && target.contains(char)) {
                usedCount++
            }
        }
    }

    return if (usedCount < targetCount) LetterState.PRESENT else LetterState.ABSENT
}
@Composable
private fun Letter(
    char: Char,
    color: Color = Color(0xffff11ff),
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(
                color = color,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = char.toString(),
            fontSize = 36.sp,
            fontFamily = golosFontFamily(),
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium
        )
    }
}


@Preview
@Composable
fun WordGridTest(){
    AppTheme {
        WordGrid(
            WordGridState(
                target = "build",
                stack = listOf("bsdsd", "sssds", "ddddd"),
                currentWord = "sdsas"
            )
        )
    }
}