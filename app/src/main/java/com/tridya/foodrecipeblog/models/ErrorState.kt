package com.tridya.foodrecipeblog.models

import androidx.annotation.StringRes
import com.tridya.foodrecipeblog.R


data class ErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = R.string.empty_string,
)