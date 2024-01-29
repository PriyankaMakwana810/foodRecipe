package com.tridya.foodrecipeblog.models

import java.io.Serializable

data class User(
    val userId: String? = null,
    val emailId: String? = null,
    val password: String? = null,
    val userName: String? = null,
    val profilePicPath: String? = null,
) : Serializable
data class UserProfile(
    val userName: String,
    val profilePic: String,
    val numOfRecipes: Int,
    val followers: Int,
    val following: Int,
    val designation: String,
    val about: String,
)
