package com.herehs.worldecopy.presentation.screens.authorisation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.herehs.worldecopy.Res
import com.herehs.worldecopy.authorisation
import com.herehs.worldecopy.dont_have_account
import com.herehs.worldecopy.login
import com.herehs.worldecopy.login_placeholder
import com.herehs.worldecopy.password
import com.herehs.worldecopy.password_placeholder
import com.herehs.worldecopy.presentation.components.RoundedButton
import com.herehs.worldecopy.presentation.components.RoundedTextField
import com.herehs.worldecopy.presentation.screens.registration.RegistrationViewModel
import com.herehs.worldecopy.presentation.theme.AppTheme
import com.herehs.worldecopy.presentation.theme.golosFontFamily
import com.herehs.worldecopy.sign_in
import com.herehs.worldecopy.sign_up
import com.herehs.worldecopy.thougthful_smile
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AuthorisationScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: AuthorisationViewModel = koinViewModel(),
    onDontHaveAccountClick: () -> Unit,
    onSignInClick: () -> Unit = { }
){
    val state by viewModel.screenState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit){
        viewModel.events.collect { event ->
            when(event){
                is AuthorisationViewModel.AuthEvent.NavigateToHome -> onSignInClick()
                is AuthorisationViewModel.AuthEvent.ShowError -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }
    AuthorisationScreen(
        modifier = modifier,
        state = state,
        onLoginTextChange = viewModel::onLoginTextChange,
        onPasswordChange = viewModel::onPasswordChange,
        onToSignInClick = onDontHaveAccountClick,
        onSignInClick = viewModel::onSignInClick,
        snackbarHostState = snackbarHostState
    )
}
@Composable
fun AuthorisationScreen(
    modifier: Modifier = Modifier,
    state: AuthorisationUiState = AuthorisationUiState(),
    onLoginTextChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onSignInClick: () -> Unit = {},
    onToSignInClick: () -> Unit = {},
    snackbarHostState: SnackbarHostState
){
    Box(
        modifier = modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(alignment = Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier.height(40.dp)
            )
            Text(
                text = stringResource(Res.string.authorisation),
                fontSize = 20.sp,
                fontFamily = golosFontFamily(),
                color = MaterialTheme.colorScheme.onSurface
            )
            // smile image
            Image(
                modifier = Modifier
                    .height(236.dp)
                    .padding(vertical = 10.dp),
                painter = painterResource(Res.drawable.thougthful_smile),
                contentDescription = "",
                contentScale = ContentScale.FillHeight
            )

            // login text field block
            Text(
                text = stringResource(Res.string.login),
                fontSize = 16.sp,
                fontFamily = golosFontFamily(),
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 30.dp, bottom = 4.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
            RoundedTextField(
                value = state.login,
                onValueChange = onLoginTextChange,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                color = MaterialTheme.colorScheme.outline,
                placeholder = stringResource(Res.string.login_placeholder)
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )


            // password text field block
            Text(
                text = stringResource(Res.string.password),
                fontSize = 16.sp,
                fontFamily = golosFontFamily(),
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 30.dp, bottom = 4.dp),
                color = MaterialTheme.colorScheme.onSurface

            )
            RoundedTextField(
                value = state.password,
                onValueChange = onPasswordChange,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                color = MaterialTheme.colorScheme.outline,
                placeholder = stringResource(Res.string.password_placeholder)
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )

        }
        //sing-in button
        Column(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RoundedButton(
                onClick = onSignInClick,
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                color = MaterialTheme.colorScheme.tertiary,
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = stringResource(Res.string.sign_in),
                    fontSize = 16.sp,
                    fontFamily = golosFontFamily(),
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Row(
                modifier = Modifier
                    .height(65.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = stringResource(Res.string.dont_have_account) + " ",
                    fontSize = 16.sp,
                    fontFamily = golosFontFamily(),
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(Res.string.sign_up),
                    fontSize = 16.sp,
                    fontFamily = golosFontFamily(),
                    modifier = Modifier.clickable(
                        onClick = onToSignInClick
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .imePadding()
                .padding(16.dp)
        )
    }
}

@Preview(locale = "en")
@Preview(locale = "ru")
@Composable
fun RegistrationScreenTest(){
    AppTheme {
        Scaffold { paddingValues ->
//            AuthorisationScreen(
//                modifier = Modifier.padding(paddingValues)
//            )
        }
    }
}