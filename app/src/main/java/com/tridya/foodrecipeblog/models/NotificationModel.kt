package com.tridya.foodrecipeblog.models

data class NotificationModel(
    val title: String,
    val message: String,
    val time: Long,
    val imageLink: String = ""
)