package com.tridya.foodrecipeblog.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.tridya.foodrecipeblog.models.Response
import com.tridya.foodrecipeblog.viewModels.LoginViewModel

@Composable
fun OneTapSignIn(
    viewModel: LoginViewModel = hiltViewModel(),
    launch: (result: BeginSignInResult) -> Unit,
) {
    when (val oneTapSignInResponse = viewModel.oneTapSignInResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> oneTapSignInResponse.data?.let {
            LaunchedEffect(it) {
                launch(it)
            }
        }

        is Response.Failure -> LaunchedEffect(Unit) {
            print(oneTapSignInResponse.e)
        }
    }
}