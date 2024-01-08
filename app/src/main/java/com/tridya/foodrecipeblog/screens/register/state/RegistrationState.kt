package com.tridya.foodrecipeblog.screens.register.state

import com.tridya.foodrecipeblog.models.ErrorState

data class RegistrationState(
    val emailId: String = "",
    val name: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorState: RegistrationErrorState = RegistrationErrorState(),
    val isRegistrationSuccessful: Boolean = false,
)

data class RegistrationErrorState(
    val emailIdErrorState: ErrorState = ErrorState(),
    val nameErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val confirmPasswordErrorState: ErrorState = ErrorState(),
)