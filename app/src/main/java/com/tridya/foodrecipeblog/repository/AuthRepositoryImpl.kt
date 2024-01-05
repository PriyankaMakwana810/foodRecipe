package com.tridya.foodrecipeblog.repository

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.tridya.foodrecipeblog.models.Response
import com.tridya.foodrecipeblog.utils.Constants.CREATED_AT
import com.tridya.foodrecipeblog.utils.Constants.DISPLAY_NAME
import com.tridya.foodrecipeblog.utils.Constants.EMAIL
import com.tridya.foodrecipeblog.utils.Constants.PHOTO_URL
import com.tridya.foodrecipeblog.utils.Constants.SHARED_COMMON
import com.tridya.foodrecipeblog.utils.Constants.SIGN_IN_REQUEST
import com.tridya.foodrecipeblog.utils.Constants.SIGN_UP_REQUEST
import com.tridya.foodrecipeblog.utils.Constants.USER
import com.tridya.foodrecipeblog.utils.PrefUtils
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val client: SignInClient,
    @Named(SIGN_IN_REQUEST) private val signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST) private val signUpRequest: BeginSignInRequest,
    @Named(SHARED_COMMON) private val sharedPreferences: PrefUtils,
) : AuthRepository {

    override val isUserAuthenticatedInFirebase = auth.currentUser != null

    override suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse {
        return try {
            val signInResult = client.beginSignIn(signInRequest).await()
            Response.Success(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = client.beginSignIn(signUpRequest).await()
                Response.Success(signUpResult)
            } catch (e: Exception) {
                Response.Failure(e)
            }
        }
    }

    override suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): SignInWithGoogleResponse {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addUserToDatabase()

            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    private suspend fun addUserToDatabase() {
        auth.currentUser?.apply {
            val user = toUser()
            sharedPreferences.putDataClass(USER, user)
        }
    }
}

fun FirebaseUser.toUser() = mapOf(
    DISPLAY_NAME to displayName,
    EMAIL to email,
    PHOTO_URL to photoUrl?.toString(),
    CREATED_AT to FieldValue.serverTimestamp()
)