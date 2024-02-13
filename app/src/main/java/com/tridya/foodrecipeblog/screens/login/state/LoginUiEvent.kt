package com.tridya.foodrecipeblog.screens.login.state

/**
 * Login Screen Events
 */
sealed class LoginUiEvent {
    data class EmailChanged(val inputValue: String) : LoginUiEvent()
    data class PasswordChanged(val inputValue: String) : LoginUiEvent()
    data object Submit : LoginUiEvent()
}