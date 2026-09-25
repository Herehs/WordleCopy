package com.herehs.worldecopy.presentation.screens.authorisation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herehs.worldecopy.core.util.Resource
import com.herehs.worldecopy.domain.usecase.SignInUseCase
import com.herehs.worldecopy.presentation.screens.registration.RegistrationViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthorisationViewModel(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _eventChannel = Channel<AuthEvent>(Channel.BUFFERED)
    val events = _eventChannel.receiveAsFlow()

    sealed interface AuthEvent {
        data object NavigateToHome : AuthEvent
        data class ShowError(val message: String) : AuthEvent
    }
    private val _screenState = MutableStateFlow(AuthorisationUiState())
    val screenState = _screenState.asStateFlow()


    fun onLoginTextChange(value: String) = _screenState.update { it.copy(login = value) }
    fun onPasswordChange(value: String) = _screenState.update { it.copy(password = value) }

    fun onSignInClick(){
        viewModelScope.launch {
            signInUseCase(
                username = screenState.value.login,
                password = screenState.value.password
            ).collect { result ->
                when(result){
                    is Resource.Error<Unit> -> {
                        _screenState.update { state ->
                            state.copy(isLoading = false, error = result.message)
                        }
                        _eventChannel.send(AuthEvent.ShowError(result.message ?: ""))
                    }
                    is Resource.Loading<Unit> -> {
                        _screenState.update { state ->
                            state.copy(isLoading = true)
                        }
                    }
                    is Resource.Success<Unit> -> {
                        _screenState.update { state ->
                            state.copy(isLoading = false)
                        }
                        _eventChannel.send(AuthEvent.NavigateToHome)
                    }
                }
            }
        }
    }
}