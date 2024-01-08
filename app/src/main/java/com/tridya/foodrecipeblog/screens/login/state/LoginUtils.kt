package com.tridya.foodrecipeblog.screens.login.state

import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.models.ErrorState

val emailEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_email
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)