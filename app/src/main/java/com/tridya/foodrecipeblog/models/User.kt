package com.tridya.foodrecipeblog.models

import java.io.Serializable

data class User(
    val userId: Int? = null,
    val emailId: String? = null,
    val password: String? = null,
    val userName: String? = null,
    val profilePicPath: String? = null,
) : Serializable