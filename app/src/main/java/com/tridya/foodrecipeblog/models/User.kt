package com.tridya.foodrecipeblog.models

import java.io.Serializable

data class User(
    val userId: String? = null,
    val emailId: String? = null,
    val password: String? = null,
    val userName: String? = null,
    val profilePicPath: String? = null,
) : Serializable