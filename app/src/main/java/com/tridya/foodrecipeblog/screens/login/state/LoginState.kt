package com.tridya.foodrecipeblog.screens.login.state

import com.tridya.foodrecipeblog.models.ErrorState


/**
 * Login State holding ui input values
 */
data class LoginState(
    val email: String = "priyanka@gmail.com",
    val password: String = "123456",
    val errorState: LoginErrorState = LoginErrorState(),
    val isLoginSuccessful: Boolean = false
)

/**
 * Error state in login holding respective
 * text field validation errors
 */
data class LoginErrorState(
    val emailErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
)

