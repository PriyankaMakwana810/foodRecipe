package com.tridya.foodrecipeblog.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tridya.foodrecipeblog.database.dao.RecipeDao
import com.tridya.foodrecipeblog.models.RecipeCard

@Database(entities = [RecipeCard::class], version = 1, exportSchema = false)
abstract class FoodRecipeDatabase : RoomDatabase() {
    abstract fun recipeDao():RecipeDao
}