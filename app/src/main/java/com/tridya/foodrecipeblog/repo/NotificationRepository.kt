package com.tridya.foodrecipeblog.repo

import com.tridya.foodrecipeblog.database.dao.NotificationDao
import com.tridya.foodrecipeblog.database.tables.Notifications
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val notificationDao: NotificationDao,
) {

    val getAllNotifications: Flow<List<Notifications>> = notificationDao.getAllNotifications()
    val getReadNotifications: Flow<List<Notifications>> = notificationDao.getReadNotifications()
    val getUnreadNotifications: Flow<List<Notifications>> = notificationDao.getUnreadNotifications()
    suspend fun updateNotificationState(isRead: Boolean, notificationId: Int) {
        notificationDao.updateNotificationRead(isRead = isRead, notificationId = notificationId)
    }
}