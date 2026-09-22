package com.herehs.worldecopy.presentation.screens.registration

data class RegistrationUiState(
    val login: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
){
    val isEquals: Boolean
        get() = password == confirmPassword

    val canSubmit: Boolean
        get() = login.isNotEmpty() && password.isNotEmpty() && isEquals
}
