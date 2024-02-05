package com.tridya.foodrecipeblog.database

import android.content.Context
import androidx.room.Room
import com.tridya.foodrecipeblog.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        FoodRecipeDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: FoodRecipeDatabase) = database.recipeDetailsDao()

    @Singleton
    @Provides
    fun provideRecipeDao(database: FoodRecipeDatabase) = database.recipeDao()

    @Singleton
    @Provides
    fun provideNotificationDao(database: FoodRecipeDatabase) = database.notificationDao()
}

