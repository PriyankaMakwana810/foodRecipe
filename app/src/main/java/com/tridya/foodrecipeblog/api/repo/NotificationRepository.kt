package com.tridya.foodrecipeblog.api.repo

import com.tridya.foodrecipeblog.database.dao.NotificationDao
import com.tridya.foodrecipeblog.database.dao.RecipeDao
import com.tridya.foodrecipeblog.database.tables.Notifications
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val recipeDao: RecipeDao,
    private val notificationDao: NotificationDao,
) {
    val getAllRecipes: Flow<List<RecipeCard>> = recipeDao.getAllRecipes()

    val getAllNotifications: Flow<List<Notifications>> = notificationDao.getAllNotifications()
    val getReadNotifications: Flow<List<Notifications>> = notificationDao.getReadNotifications()
    val getUnreadNotifications: Flow<List<Notifications>> = notificationDao.getUnreadNotifications()
    suspend fun updateNotificationState(isRead: Boolean, notificationId: Int) {
        notificationDao.updateNotificationRead(isRead = isRead, notificationId = notificationId)
    }
}