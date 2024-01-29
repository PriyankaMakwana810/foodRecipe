package com.tridya.foodrecipeblog.models

import java.security.cert.CertPath

data class ReviewModel(
    val id: Int,
    val userName: String,
    val profilePicPath: String,
    val time: String,
    val comment: String,
    val likedCount: Int = 0,
    val disLikedCount: Int = 0,
)
