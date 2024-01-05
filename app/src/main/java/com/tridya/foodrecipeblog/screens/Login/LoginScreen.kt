package com.tridya.foodrecipeblog.screens.Login

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.AuthContent
import com.tridya.foodrecipeblog.components.ButtonComponent
import com.tridya.foodrecipeblog.components.ClickableTextLoginComponent
import com.tridya.foodrecipeblog.components.DividerTextComponent
import com.tridya.foodrecipeblog.components.OneTapSignIn
import com.tridya.foodrecipeblog.components.ProgressBar
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.components.SmallTextLabel
import com.tridya.foodrecipeblog.components.SocialIcons
import com.tridya.foodrecipeblog.components.TextFieldCustom
import com.tridya.foodrecipeblog.components.TextFieldPassword
import com.tridya.foodrecipeblog.components.YellowSmallText
import com.tridya.foodrecipeblog.models.Response
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.toToast
import com.tridya.foodrecipeblog.viewModels.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {

    val context = LocalContext.current.applicationContext


    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(white)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            SimpleTextComponent(
                value = stringResource(R.string.hello),
                fontSize = 30.sp,
                fontWeight = FontWeight(600),
                textColor = black,
                textAlign = TextAlign.Left
            )

            SimpleTextComponent(
                value = stringResource(R.string.welcome_back),
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                textColor = black,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(30.dp))

            SmallTextLabel(value = stringResource(R.string.email))
            TextFieldCustom(
                hintText = stringResource(R.string.enter_email), onTextChanged = {}
            )

            Spacer(modifier = Modifier.height(30.dp))
            SmallTextLabel(value = stringResource(R.string.password))
            TextFieldPassword(hintText = stringResource(R.string.enter_password))
            Spacer(modifier = Modifier.height(20.dp))
            YellowSmallText(value = stringResource(R.string.forgot_password), onClick = {
                context.getString(R.string.this_functionality_is_under_development).toToast(context)
            })
            Spacer(modifier = Modifier.height(20.dp))
            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                value = stringResource(R.string.sign_in),
                onButtonClicked = {
//                    navController.navigate(Screen.RegisterScreen.route)
                })
            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()
            Spacer(modifier = Modifier.height(20.dp))
            SocialIcons()
            Spacer(modifier = Modifier.height(50.dp))
            ClickableTextLoginComponent(tryingToLogin = false, onTextSelected = {
                navController.navigate(Screen.RegisterScreen.route)
            })
            AuthContent(oneTapSignIn = {
                viewModel.oneTapSignIn()
            })
        }

    }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    val credentials = viewModel.client.getSignInCredentialFromIntent(result.data)
                    val googleIdToken = credentials.googleIdToken
                    val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                    viewModel.signInWithGoogle(googleCredentials)
                } catch (it: ApiException) {
                    print(it)
                }
            }
        }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }
    OneTapSignIn(
        launch = {
            launch(it)
        }
    )
    SignInWithGoogle(
        navigateToHomeScreen = { signedIn ->
            if (signedIn) {
                navController.navigate(Screen.IntroScreen.route)
//                navigateToProfileScreen()
            }
        })
}


@Composable
fun SignInWithGoogle(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHomeScreen: (signedIn: Boolean) -> Unit,
) {
    when (val signInWithGoogleResponse = viewModel.signInWithGoogleResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> signInWithGoogleResponse.data?.let { signedIn ->
            LaunchedEffect(signedIn) {
                navigateToHomeScreen(signedIn)
            }
        }

        is Response.Failure -> LaunchedEffect(Unit) {
            print(signInWithGoogleResponse.e)
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController())
}