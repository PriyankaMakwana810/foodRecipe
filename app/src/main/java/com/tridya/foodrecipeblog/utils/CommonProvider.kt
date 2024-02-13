package com.tridya.foodrecipeblog.utils

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.tridya.foodrecipeblog.utils.Constants.CLIENT_ID
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


    @Provides
    fun provideAppContext(@ApplicationContext context: Context) = context

    @Singleton
    @Named(SHARED_COMMON)
    @Provides
    fun providesSharedPreference(@ApplicationContext context: Context): PrefUtils =
        PrefUtils(context)
    @Provides
    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(CLIENT_ID)
            .build()

        return GoogleSignIn.getClient(context, signInOptions)
    }

//    single { getGoogleSignInClient(androidContext()) }
}