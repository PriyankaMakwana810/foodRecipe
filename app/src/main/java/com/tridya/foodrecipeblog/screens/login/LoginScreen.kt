package com.tridya.foodrecipeblog.screens.login

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.getUserFromTokenId
import com.stevdzasan.onetap.rememberOneTapSignInState
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.ButtonComponent
import com.tridya.foodrecipeblog.components.ClickableTextLoginComponent
import com.tridya.foodrecipeblog.components.DividerTextComponent
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.components.SmallTextLabel
import com.tridya.foodrecipeblog.components.SocialLoginSection
import com.tridya.foodrecipeblog.components.TextFieldCustom
import com.tridya.foodrecipeblog.components.TextFieldPassword
import com.tridya.foodrecipeblog.components.YellowSmallText
import com.tridya.foodrecipeblog.models.User
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.screens.login.state.LoginUiEvent
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.CommonProvider.getGoogleSignInClient
import com.tridya.foodrecipeblog.utils.showShortToast
import com.tridya.foodrecipeblog.utils.toToast
import com.tridya.foodrecipeblog.viewModels.LoginViewModel
import org.json.JSONException

@SuppressLint("VisibleForTests")
@Composable
fun LoginScreen(navController: NavController, paddingValues: PaddingValues) {

    val loginViewModel: LoginViewModel = hiltViewModel()
    val context = LocalContext.current

    val loginState by remember { loginViewModel.loginState }
    val callbackManager = remember { CallbackManager.Factory.create() }

    val googleSignInClient = getGoogleSignInClient(LocalContext.current.applicationContext)

    LaunchedEffect(loginState) {
        if (loginState.isLoginSuccessful) {
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo(Screen.IntroScreen.route) {
                    inclusive = false
                }
            }
        }
    }
    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (result.data != null) {
                    val task: Task<GoogleSignInAccount> =
                        GoogleSignIn.getSignedInAccountFromIntent(intent)
                    loginViewModel.handleResult(task)
                }
            }
        }
    val fbLauncher = rememberLauncherForActivityResult(
        LoginManager.getInstance().createLogInActivityResultContract(callbackManager)
    ) { result ->
        LoginManager.getInstance().onActivityResult(
            result.resultCode,
            result.data,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    val graphRequest =
                        GraphRequest.newMeRequest(
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
                                val profilePicUrl = if (obj.has("picture")) {
                                    obj.getJSONObject("picture").getJSONObject("data")
                                        .getString("url")
                                } else {
                                    null
                                }

                                Log.e("TAG", "onSuccess: $userName $id $emailId $profilePicUrl")
                                val user =
                                    User(
                                        userId = id,
                                        userName = userName,
                                        emailId = emailId,
                                        profilePicPath = profilePicUrl
                                    )
                                loginViewModel.sharedPreferences.user = user

                                navController.navigate(Screen.HomeScreen.route) {
                                    popUpTo(Screen.IntroScreen.route) {
                                        inclusive = false
                                    }
                                }
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    val bundle1 = Bundle()
                    bundle1.putString(
                        "fields",
                        "first_name,last_name,email,id, picture.type(large)"
                    )
                    graphRequest.parameters = bundle1
                    graphRequest.executeAsync()
                }

                override fun onCancel() {
                    Log.d("TAG- FacebookLogin", "onCancel: " + "cancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d("TAG- FacebookLogin", "onCancel: " + error.message)
                }
            }
        )
    }

    val oneTapSignInState = rememberOneTapSignInState()
    var authenticated by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
                .background(white)
                .verticalScroll(state = scrollState)
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
            Spacer(modifier = Modifier.height(40.dp))

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

            Spacer(modifier = Modifier.height(10.dp))

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
            Spacer(modifier = Modifier.height(25.dp))
            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                value = stringResource(R.string.sign_in),
                onButtonClicked = {
                    loginViewModel.onUiEvent(loginUiEvent = LoginUiEvent.Submit)
                })
            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()
            Spacer(modifier = Modifier.height(10.dp))
            SocialLoginSection(onClickGoogle = {
                startForResult.launch(googleSignInClient.signInIntent)
//                oneTapSignInState.open()
            }, onClickFacebook = {
                fbLauncher.launch(listOf("email", "public_profile"))
            })
            Spacer(modifier = Modifier.height(45.dp))
            ClickableTextLoginComponent(tryingToLogin = false, onTextSelected = {
                navController.navigate(Screen.RegisterScreen.route)
            })
        }

    }
    OneTapSignInWithGoogle(
        state = oneTapSignInState,
        clientId = stringResource(id = R.string.google_one_tap_client_id),
        rememberAccount = false,
        onTokenIdReceived = { tokenId ->
            authenticated = false
            Log.d("LoginScreen: ", tokenId)
            Log.e("LOG", tokenId)
            Log.e("LOG", "LoginScreen: ${getUserFromTokenId(tokenId)?.fullName}")
            val user = getUserFromTokenId(tokenId)
            val user1 =
                User(
                    userId = user?.sub,
                    userName = user?.fullName,
                    emailId = user?.email,
                    profilePicPath = user?.picture
                )
            loginViewModel.sharedPreferences.user = user1
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo(Screen.IntroScreen.route) {
                    inclusive = false
                }
            }

            Toast.makeText(context, user?.fullName, Toast.LENGTH_SHORT).show()
        },
        onDialogDismissed = { message ->
            Log.d("LOG", message)
            showShortToast(context, message)
        }
    )
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController(), paddingValues = PaddingValues())
}

//val scope = rememberCoroutineScope()
//val loginManager = LoginManager.getInstance()

/*    val fbLauncher = rememberLauncherForActivityResult(
        loginManager.createLogInActivityResultContract(callbackManager, null)
    ) {
        // nothing to do. handled in FacebookCallback
    }
    DisposableEffect(Unit) {
        loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onCancel() {
                // do nothing
                Log.d("TAG- FacebookLogin", "onCancel: " + "cancel")
            }

            override fun onError(error: FacebookException) {
                Log.d("TAG- FacebookLogin", "onCancel: " + error.message)
            }

            override fun onSuccess(result: LoginResult) {
                // user signed in successfully
                val graphRequest =
                    GraphRequest.newMeRequest(
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
                            val profilePicUrl =
                                obj.getJSONObject("picture").getJSONObject("data").getString("url")
                            Log.e("TAG", "onSuccess: $userName $id $emailId")
                            val user =
                                User(
                                    userId = id,
                                    userName = userName,
                                    emailId = emailId,
                                    profilePicPath = profilePicUrl
                                )
                            loginViewModel.sharedPreferences.user = user

                            *//*loginViewModel.onUiEvent(
                                loginUiEvent = LoginUiEvent.EmailChanged(
                                    inputValue = emailId
                                )
                            )*//*
                            navController.navigate(Screen.HomeScreen.route)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                val bundle1 = Bundle()
                bundle1.putString(
                    "fields",
                    "first_name,last_name,email,id,link, picture.type(large)"
                )
                graphRequest.parameters = bundle1
                graphRequest.executeAsync()

            }
        })
        onDispose {
            loginManager.unregisterCallback(callbackManager)
        }
    }*/