package com.tridya.foodrecipeblog.screens.register

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.getUserFromTokenId
import com.stevdzasan.onetap.rememberOneTapSignInState
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.ButtonComponent
import com.tridya.foodrecipeblog.components.CheckboxComponent
import com.tridya.foodrecipeblog.components.ClickableTextLoginComponent
import com.tridya.foodrecipeblog.components.DividerTextComponent
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.components.SmallTextLabel
import com.tridya.foodrecipeblog.components.SocialIcons
import com.tridya.foodrecipeblog.components.TextFieldCustom
import com.tridya.foodrecipeblog.components.TextFieldPassword
import com.tridya.foodrecipeblog.models.User
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.screens.register.state.RegistrationUiEvent
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.viewModels.RegisterViewModel
import org.json.JSONException

@Composable
fun RegisterScreen(navController: NavController) {
    val registrationViewModel: RegisterViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val registrationState by remember {
        registrationViewModel.registrationState
    }
    val callbackManager = remember {
        CallbackManager.Factory.create()
    }
    val fbLauncher = rememberLauncherForActivityResult(
        LoginManager.getInstance().createLogInActivityResultContract(callbackManager)
    ) { result ->
        LoginManager.getInstance().onActivityResult(result.resultCode,
            result.data,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    val graphRequest = GraphRequest.newMeRequest(
                        result.accessToken
                    ) { obj, _ ->
                        try {
                            val userName =
                                obj!!.getString("first_name") + obj.getString("last_name")
                            val id = obj.getString("id")
                            val emailId = if (obj.has("email")) {
                                obj.getString("email")
                            } else {
                                null
                            }
                            Log.e("TAG", "onSuccess: $userName $id $emailId")
                            val user =
                                User(
                                    userId = id.toInt(),
                                    userName = userName,
                                    emailId = emailId,
                                )
                            registrationViewModel.sharedPreferences.user = user
                            registrationViewModel.onUiEvent(
                                registrationUiEvent = RegistrationUiEvent.EmailChanged(
                                    inputValue = emailId!!
                                )
                            )
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    val bundle1 = Bundle()
                    bundle1.putString("fields", "first_name,last_name,email,id")
                    graphRequest.parameters = bundle1
                    graphRequest.executeAsync()
                }

                override fun onCancel() {
                    Log.d("TAG- FacebookLogin", "onCancel: " + "cancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d("TAG- FacebookLogin", "onCancel: " + error.message)
                }
            })
    }
    val oneTapSignInState = rememberOneTapSignInState()
    var authenticated by remember {
        mutableStateOf(false)
    }

    OneTapSignInWithGoogle(state = oneTapSignInState,
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
        })

    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(white)
                .verticalScroll(state = scrollState)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            SimpleTextComponent(
                value = stringResource(R.string.create_an_account),
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                textColor = black,
                textAlign = TextAlign.Left
            )

            SimpleTextComponent(
                value = stringResource(R.string.let_s_help_you_set_up_your_account_it_won_t_take_long),
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                textColor = black,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.height(10.dp))

            SmallTextLabel(value = stringResource(R.string.name))
            TextFieldCustom(
                value = registrationState.name,
                hintText = stringResource(R.string.enter_name),
                onTextChanged = {
                    registrationViewModel.onUiEvent(
                        registrationUiEvent = RegistrationUiEvent.NameChanged(
                            inputValue = it
                        )
                    )
                },
                isError = registrationState.errorState.nameErrorState.hasError,
                errorText = stringResource(id = registrationState.errorState.nameErrorState.errorMessageStringResource)
            )

            SmallTextLabel(value = stringResource(R.string.email))
            TextFieldCustom(
                value = registrationState.emailId,
                hintText = stringResource(R.string.enter_email),
                onTextChanged = {
                    registrationViewModel.onUiEvent(
                        registrationUiEvent = RegistrationUiEvent.EmailChanged(
                            inputValue = it
                        )
                    )
                },
                isError = registrationState.errorState.emailIdErrorState.hasError,
                errorText = stringResource(id = registrationState.errorState.emailIdErrorState.errorMessageStringResource)
            )

            SmallTextLabel(value = stringResource(R.string.password))
            TextFieldPassword(
                value = registrationState.password,
                hintText = stringResource(R.string.enter_password),
                onValueChange = {
                    registrationViewModel.onUiEvent(
                        registrationUiEvent = RegistrationUiEvent.PasswordChanged(
                            inputValue = it
                        )
                    )
                },
                isError = registrationState.errorState.passwordErrorState.hasError,
                errorText = stringResource(id = registrationState.errorState.passwordErrorState.errorMessageStringResource)
            )

            SmallTextLabel(value = stringResource(R.string.confirm_password))
            TextFieldPassword(
                value = registrationState.confirmPassword,
                hintText = stringResource(R.string.retype_password),
                onValueChange = {
                    registrationViewModel.onUiEvent(
                        registrationUiEvent = RegistrationUiEvent.ConfirmPasswordChanged(
                            inputValue = it
                        )
                    )
                },
                isError = registrationState.errorState.confirmPasswordErrorState.hasError,
                errorText = stringResource(id = registrationState.errorState.confirmPasswordErrorState.errorMessageStringResource)
            )

            Spacer(modifier = Modifier.height(20.dp))

            CheckboxComponent(value = "Accept terms & Condition")
//            YellowSmallText(value = stringResource(R.string.forgot_password))

            Spacer(modifier = Modifier.height(20.dp))

            ButtonComponent(modifier = Modifier.fillMaxWidth(),
                value = stringResource(R.string.sign_in),
                onButtonClicked = {
//                    navController.navigate(Screen.RegisterScreen.route)
                    registrationViewModel.onUiEvent(registrationUiEvent = RegistrationUiEvent.Submit)
                    if (registrationState.isRegistrationSuccessful) {
                        navController.navigate(Screen.HomeScreen.route)
                    }

                })
            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()
            Spacer(modifier = Modifier.height(20.dp))
            SocialIcons(onClickGoogle = {
                oneTapSignInState.open()
            }, onClickFacebook = {
                fbLauncher.launch(listOf("email", "public_profile"))
            })
            Spacer(modifier = Modifier.height(50.dp))
            ClickableTextLoginComponent(tryingToLogin = true, onTextSelected = {
                navController.navigate(Screen.LoginScreen.route)
            })
        }

    }
}


@Preview
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(navController = rememberNavController())
}