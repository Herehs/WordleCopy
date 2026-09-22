package com.herehs.worldecopy.presentation.screens.registration

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegistrationViewModel() : ViewModel() {
    private val _screenState = MutableStateFlow(RegistrationUiState())
    val screenState = _screenState.asStateFlow()


    fun onLoginTextChange(value: String) = _screenState.update { it.copy(login = value) }
    fun onPasswordChange(value: String) = _screenState.update { it.copy(password = value) }
    fun onConfirmPasswordChange(value: String) = _screenState.update { it.copy(confirmPassword = value) }
}