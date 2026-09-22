package com.herehs.worldecopy.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RoundedTextField(
    value: String = "",
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    textStyle: TextStyle = TextStyle.Default
) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ){
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            maxLines = 1,
            singleLine = true,
            textStyle = textStyle,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(
                            color = color
                        )
                        .matchParentSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.CenterStart
                ){
                    innerTextField()
                }
            }
        )

    }
}

data class RoundedTextFieldState(
    val value: String
)


@Composable
@Preview
fun RoundedTextFieldTest(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        var text by remember { mutableStateOf("") }
        RoundedTextField(
            modifier = Modifier.fillMaxWidth().height(56.dp),
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(
                fontSize = 16.sp,
            )
        )
    }
}