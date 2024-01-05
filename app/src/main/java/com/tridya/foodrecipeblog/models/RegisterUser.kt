package com.tridya.foodrecipeblog.models

data class RegisterUser(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = ""
)
