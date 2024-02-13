package com.tridya.foodrecipeblog.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tridya.foodrecipeblog.database.dao.NotificationDao
import com.tridya.foodrecipeblog.database.dao.RecipeDao
import com.tridya.foodrecipeblog.database.tables.Notifications
import com.tridya.foodrecipeblog.database.tables.RecipeCard

@Database(
    entities = [RecipeCard::class, Notifications::class],
    version = 1,
    exportSchema = false
)
abstract class FoodRecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun notificationDao(): NotificationDao
}