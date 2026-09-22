package com.herehs.worldecopy.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.CenterStart,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(
                color = color
            )
            .clickable(
                onClick = onClick
            )
            .height(56.dp)
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = contentAlignment
    ){
        content()
    }
}

@Preview
@Composable
fun RoundedButtonPreview(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ){
        RoundedButton(
            onClick = {},
            color = MaterialTheme.colorScheme.tertiary
        ){
            Text("sds")
        }

    }
}