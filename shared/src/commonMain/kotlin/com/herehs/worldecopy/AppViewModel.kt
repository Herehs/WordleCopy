package com.herehs.worldecopy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herehs.worldecopy.domain.repository.TokenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

sealed interface AuthState {
    data object Loading : AuthState
    data object Authenticated : AuthState
    data object Unauthenticated : AuthState
}
class AppViewModel(
    private val tokenRepository: TokenRepository
): ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState = _authState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = tokenRepository.get().firstOrNull()
            when(result){
                null -> {
                    _authState.value = AuthState.Unauthenticated
                }
                else -> {
                    _authState.value = AuthState.Authenticated
                }
            }
        }
    }
}