package com.herehs.worldecopy.presentation.screens.authorisation

data class AuthorisationUiState(
    val login: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val canSubmit: Boolean
        get() = login.isNotEmpty() && password.isNotEmpty()
}
