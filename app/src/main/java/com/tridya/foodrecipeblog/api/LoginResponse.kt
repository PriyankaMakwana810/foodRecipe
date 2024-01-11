package com.tridya.foodrecipeblog.api

data class LoginResponse(
    val IsSuccess: Boolean,
    val Message: String,
    val ResponseCode: Int,
    val Time: String,
)
