package com.herehs.worldecopy.presentation.screens.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.herehs.worldecopy.Res
import com.herehs.worldecopy.cool_smile
import com.herehs.worldecopy.presentation.components.RoundedButton
import com.herehs.worldecopy.presentation.screens.main.MainScreenViewModel
import com.herehs.worldecopy.presentation.theme.AppTheme
import com.herehs.worldecopy.presentation.theme.golosFontFamily
import com.herehs.worldecopy.registration
import com.herehs.worldecopy.sad_smile
import com.herehs.worldecopy.scary_smile
import io.ktor.websocket.Frame
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FinishGameDialog(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    event: MainScreenViewModel.GameEvent
){
    Column(
        modifier = modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(20.dp)
            )
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth(.9f)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        when(event){
            MainScreenViewModel.GameEvent.Lost -> {
                Image(
                    modifier = Modifier
                        .height(236.dp)
                        .padding(vertical = 10.dp),
                    painter = painterResource(Res.drawable.sad_smile),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )
                Text(
                    text = "Не расстраивайся, в следующий раз получится!",
                    fontSize = 16.sp,
                    fontFamily = golosFontFamily(),
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

            }
            MainScreenViewModel.GameEvent.Won -> {
                Image(
                    modifier = Modifier
                        .height(236.dp)
                        .padding(vertical = 10.dp),
                    painter = painterResource(Res.drawable.cool_smile),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )
                Text(
                    text = "Отлично получилось!",
                    fontSize = 16.sp,
                    fontFamily = golosFontFamily(),
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        RoundedButton(
            onClick = onClick,
            contentAlignment = Alignment.Center,
            color = MaterialTheme.colorScheme.tertiary,
        ){
            Text(
                text = "В главное меню",
                fontSize = 16.sp,
                fontFamily = golosFontFamily(),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(backgroundColor = 0xffffffff, showBackground = true)
@Composable
fun Test(){
    AppTheme {

        Box(
            modifier = Modifier
                .background(Color.Black.copy(alpha = .5f))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            FinishGameDialog(
                onClick = {  },
                modifier = Modifier,
                event = MainScreenViewModel.GameEvent.Won
            )
        }

    }
}