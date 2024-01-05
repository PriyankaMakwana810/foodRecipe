package com.tridya.foodrecipeblog.module

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class FirebaseGoogleModule {


    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("YOUR_WEB_CLIENT_ID") // Replace with your web client ID
        .requestEmail()
        .build()
}