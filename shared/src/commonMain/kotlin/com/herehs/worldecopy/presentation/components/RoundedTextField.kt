package com.herehs.worldecopy.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
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
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.herehs.worldecopy.Res
import com.herehs.worldecopy.eye
import com.herehs.worldecopy.eye_off
import org.jetbrains.compose.resources.painterResource


@Composable
fun RoundedTextField(
    value: String = "",
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    textStyle: TextStyle = TextStyle.Default,
    placeholder: String = "",
    placeholderColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    isPassword: Boolean = false,
    passwordVisibilityIconColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    val showPlaceholder = value.isEmpty()
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.align(Alignment.CenterStart),
            maxLines = 1,
            singleLine = true,
            textStyle = textStyle.copy(
                color = textColor
            ),
            visualTransformation = if (isPassword && !passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = if (isPassword) {
                KeyboardOptions(keyboardType = KeyboardType.Password)
            } else {
                KeyboardOptions.Default
            },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = color)
                        .matchParentSize()
                        .padding(start = 20.dp, end = 5.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            if (showPlaceholder) {
                                Text(
                                    text = placeholder,
                                    style = textStyle.copy(
                                        color = placeholderColor
                                    )
                                )
                            }
                            innerTextField()
                        }

                        if (isPassword) {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    painter = if (passwordVisible) {
                                        painterResource(Res.drawable.eye)
                                    } else {
                                        painterResource(Res.drawable.eye_off)
                                    },
                                    contentDescription = if (passwordVisible) {
                                        "Скрыть пароль"
                                    } else {
                                        "Показать пароль"
                                    },
                                    tint = passwordVisibilityIconColor
                                )
                            }
                        }
                    }
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
        var text by remember { mutableStateOf("sdsdsd") }
        RoundedTextField(
            modifier = Modifier.fillMaxWidth().height(56.dp),
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(
                fontSize = 16.sp,
            ),
            isPassword = true
        )
    }
}