package com.tridya.foodrecipeblog.api.response

data class LoginResponse(
    val IsSuccess: Boolean,
    val Message: String,
    val ResponseCode: Int,
    val Time: String,
)
