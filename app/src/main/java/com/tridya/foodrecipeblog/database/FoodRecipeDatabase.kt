package com.tridya.foodrecipeblog.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tridya.foodrecipeblog.database.dao.NotificationDao
import com.tridya.foodrecipeblog.database.dao.RecipeDao
import com.tridya.foodrecipeblog.database.dao.RecipeDetailsDao
import com.tridya.foodrecipeblog.database.tables.Notifications
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import com.tridya.foodrecipeblog.database.tables.RecipeDetails

@Database(
    entities = [RecipeCard::class, RecipeDetails::class, Notifications::class],
    version = 1,
    exportSchema = false
)
abstract class FoodRecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeDetailsDao(): RecipeDetailsDao
    abstract fun notificationDao(): NotificationDao
}