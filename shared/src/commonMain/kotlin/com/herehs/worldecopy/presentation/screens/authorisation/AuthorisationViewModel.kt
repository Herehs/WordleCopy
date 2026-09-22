package com.herehs.worldecopy.presentation.screens.authorisation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthorisationViewModel : ViewModel() {
    private val _screenState = MutableStateFlow(AuthorisationUiState())
    val screenState = _screenState.asStateFlow()


    fun onLoginTextChange(value: String) = _screenState.update { it.copy(login = value) }
    fun onPasswordChange(value: String) = _screenState.update { it.copy(password = value) }
}