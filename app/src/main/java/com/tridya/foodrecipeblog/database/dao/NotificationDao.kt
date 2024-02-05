package com.tridya.foodrecipeblog.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tridya.foodrecipeblog.database.tables.Notifications
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    @Query("SELECT * FROM notifications_table ORDER BY id ASC")
    fun getAllNotification(): Flow<List<Notifications>>

    @Query("SELECT * FROM notifications_table WHERE isRead = 0")
    fun getUnreadNotifications(): Flow<List<Notifications>>

    @Query("SELECT * FROM notifications_table WHERE isRead = 1")
    fun getReadNotifications(): Flow<List<Notifications>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNotification(notifications: Notifications): Long

    @Delete
    suspend fun deleteNotification(notifications: Notifications)

    @Query("DELETE FROM notifications_table")
    suspend fun deleteAllNotification()

    @Query("UPDATE notifications_table Set isRead =:isSaved WHERE  id = :notificationId")
    suspend fun updateNotificationRead(isSaved: Boolean, notificationId: Int)

}