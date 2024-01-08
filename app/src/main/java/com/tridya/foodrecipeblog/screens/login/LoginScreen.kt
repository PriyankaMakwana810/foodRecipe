package com.tridya.foodrecipeblog.screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.getUserFromTokenId
import com.stevdzasan.onetap.rememberOneTapSignInState
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.ButtonComponent
import com.tridya.foodrecipeblog.components.ClickableTextLoginComponent
import com.tridya.foodrecipeblog.components.DividerTextComponent
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.components.SmallTextLabel
import com.tridya.foodrecipeblog.components.TextFieldCustom
import com.tridya.foodrecipeblog.components.TextFieldPassword
import com.tridya.foodrecipeblog.components.YellowSmallText
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.screens.login.state.LoginUiEvent
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.toToast
import com.tridya.foodrecipeblog.viewModels.LoginViewModel
import androidx.compose.foundation.layout.Row as Row1

@Composable
fun LoginScreen(navController: NavController) {

    val loginViewModel: LoginViewModel = hiltViewModel()
    val context = LocalContext.current.applicationContext
    val loginState by remember {
        loginViewModel.loginState
    }

    val oneTapSignInState = rememberOneTapSignInState()
    var authenticated by remember {
        mutableStateOf(false)
    }
    OneTapSignInWithGoogle(
        state = oneTapSignInState,
        clientId = "567930687823-8g419u2eg5rom36u5dc3u078j0a6ppq3.apps.googleusercontent.com",
        rememberAccount = false,
        onTokenIdReceived = { tokenId ->
            authenticated = true
            Log.d("LoginScreen: ", tokenId)
            Log.e("LOG", tokenId)
            Log.e("LOG", "LoginScreen: ${getUserFromTokenId(tokenId)?.fullName}")
            val user = getUserFromTokenId(tokenId)
            Toast.makeText(context, user?.fullName, Toast.LENGTH_SHORT).show()
        },
        onDialogDismissed = { message ->
            Log.d("LOG", message)
        }
    )
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
                value = loginState.email,
                hintText = stringResource(R.string.enter_email),
                onTextChanged = {
                    loginViewModel.onUiEvent(
                        loginUiEvent = LoginUiEvent.EmailChanged(
                            inputValue = it
                        )
                    )
                },
                isError = loginState.errorState.emailErrorState.hasError,
                errorText = stringResource(id = loginState.errorState.emailErrorState.errorMessageStringResource)
            )

            Spacer(modifier = Modifier.height(30.dp))

            SmallTextLabel(value = stringResource(R.string.password))

            TextFieldPassword(
                value = loginState.password,
                hintText = stringResource(R.string.enter_password),
                onValueChange = {
                    loginViewModel.onUiEvent(
                        loginUiEvent = LoginUiEvent.PasswordChanged(
                            inputValue = it
                        )
                    )
                },
                isError = loginState.errorState.passwordErrorState.hasError,
                errorText = stringResource(id = loginState.errorState.passwordErrorState.errorMessageStringResource)
            )
            Spacer(modifier = Modifier.height(20.dp))
            YellowSmallText(value = stringResource(R.string.forgot_password), onClick = {
                context.getString(R.string.this_functionality_is_under_development).toToast(context)
            })
            Spacer(modifier = Modifier.height(20.dp))
            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                value = stringResource(R.string.sign_in),
                onButtonClicked = {
                    loginViewModel.onUiEvent(loginUiEvent = LoginUiEvent.Submit)
                    if (loginState.isLoginSuccessful) {
                        navController.navigate(Screen.RegisterScreen.route)
                    }
                })
            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()
            Spacer(modifier = Modifier.height(20.dp))
//            SocialIcons()
            Row1(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { oneTapSignInState.open() },
                    enabled = !oneTapSignInState.opened,
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    elevation = ButtonDefaults.buttonElevation(),
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = gray3,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google_svg),
                        contentDescription = "Google Logo",
                    )
                }
                Spacer(modifier = Modifier.width(25.dp))
                Button(
                    onClick = {

                    },
                    elevation = ButtonDefaults.buttonElevation(),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = gray3,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook_svg),
                        contentDescription = "Google Logo",
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))
            ClickableTextLoginComponent(tryingToLogin = false, onTextSelected = {
                navController.navigate(Screen.RegisterScreen.route)
            })
        }

    }

}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController())
}