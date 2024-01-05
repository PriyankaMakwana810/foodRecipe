package com.tridya.foodrecipeblog.module

import android.app.Application
import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.repository.AuthRepository
import com.tridya.foodrecipeblog.repository.AuthRepositoryImpl
import com.tridya.foodrecipeblog.utils.Constants
import com.tridya.foodrecipeblog.utils.Constants.CREDENTIAL_MANAGER
import com.tridya.foodrecipeblog.utils.Constants.SIGN_IN_REQUEST
import com.tridya.foodrecipeblog.utils.Constants.SIGN_UP_REQUEST
import com.tridya.foodrecipeblog.utils.PrefUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class AuthModule {
    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideOneTapClient(
        @ApplicationContext
        context: Context,
    ) = Identity.getSignInClient(context)

    @Provides
    @Named(SIGN_IN_REQUEST)
    fun provideSignInRequest(
        app: Application,
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.google_cloud_web_client_id))
                .setFilterByAuthorizedAccounts(true)
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()

/*
    @Provides
    @Named(CREDENTIAL_MANAGER)
    fun provideCredentialManagerRequest(app: Application) = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId(app.getString(R.string.google_cloud_web_client_id))
        .build()
*/


    @Provides
    @Named(SIGN_UP_REQUEST)
    fun provideSignUpRequest(
        app: Application,
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.google_cloud_web_client_id))
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()

    @Provides
    fun provideGoogleSignInOptions(
        app: Application,
    ) = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(app.getString(R.string.google_cloud_web_client_id))
        .requestEmail()
        .build()

    @Provides
    fun provideGoogleSignInClient(
        app: Application,
        options: GoogleSignInOptions,
    ) = GoogleSignIn.getClient(app, options)

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        client: SignInClient,
        @Named(SIGN_IN_REQUEST)
        signInRequest: BeginSignInRequest,
        @Named(SIGN_UP_REQUEST)
        signUpRequest: BeginSignInRequest,
        @Named(Constants.SHARED_COMMON)
        sharedPreferences: PrefUtils,
    ): AuthRepository = AuthRepositoryImpl(
        auth = auth,
        client = client,
        signInRequest = signInRequest,
        signUpRequest = signUpRequest,
        sharedPreferences = sharedPreferences
    )
}