package com.tridya.foodrecipeblog.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications_table")
data class Notifications(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String? = null,
    val messageBody: String? = null,
    val time: Long? = 0,
    val isRead: Boolean? = false
)