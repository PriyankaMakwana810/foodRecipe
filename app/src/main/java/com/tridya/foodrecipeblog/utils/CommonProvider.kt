package com.tridya.foodrecipeblog.utils

import android.content.Context
import com.tridya.foodrecipeblog.utils.Constants.SHARED_COMMON
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonProvider {
    @Singleton
    @Named(SHARED_COMMON)
    @Provides
    fun providesSharedPreference(@ApplicationContext context: Context): PrefUtils =
        PrefUtils(context)
}