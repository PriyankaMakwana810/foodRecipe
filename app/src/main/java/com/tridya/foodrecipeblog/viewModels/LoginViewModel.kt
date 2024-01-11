package com.tridya.foodrecipeblog.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.identity.SignInClient
import com.tridya.foodrecipeblog.api.repo.LoginRepository
import com.tridya.foodrecipeblog.models.ErrorState
import com.tridya.foodrecipeblog.models.User
import com.tridya.foodrecipeblog.repository.AuthRepository
import com.tridya.foodrecipeblog.screens.login.state.LoginErrorState
import com.tridya.foodrecipeblog.screens.login.state.LoginState
import com.tridya.foodrecipeblog.screens.login.state.LoginUiEvent
import com.tridya.foodrecipeblog.screens.login.state.emailEmptyErrorState
import com.tridya.foodrecipeblog.screens.login.state.passwordEmptyErrorState
import com.tridya.foodrecipeblog.utils.Constants.SHARED_COMMON
import com.tridya.foodrecipeblog.utils.PrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val repository: LoginRepository,
    @Named(SHARED_COMMON) private val sharedPreferences: PrefUtils,
    val client: SignInClient,
) : ViewModel() {


    private val error = MutableLiveData<String>()
    private lateinit var disposable: Disposable
    var loginState = mutableStateOf(LoginState())
        private set

    fun onUiEvent(loginUiEvent: LoginUiEvent) {
        when (loginUiEvent) {

            // Email/Mobile changed
            is LoginUiEvent.EmailChanged -> {
                loginState.value = loginState.value.copy(
                    email = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        emailErrorState = if (loginUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            emailEmptyErrorState
                    )
                )
            }

            // Password changed
            is LoginUiEvent.PasswordChanged -> {
                loginState.value = loginState.value.copy(
                    password = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        passwordErrorState = if (loginUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            passwordEmptyErrorState
                    )
                )
            }

            // Submit Login
            is LoginUiEvent.Submit -> {
                val inputsValidated = validateInputs()
//                getLoginStatus()
                if (inputsValidated) {
                    getLoginStatus()
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val emailOrMobileString = loginState.value.email.trim()
        val passwordString = loginState.value.password
        return when {

            // Email/Mobile empty
            emailOrMobileString.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        emailErrorState = emailEmptyErrorState
                    )
                )
                false
            }

            //Password Empty
            passwordString.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        passwordErrorState = passwordEmptyErrorState
                    )
                )
                false
            }

            // No errors
            else -> {
                // Set default error state
                loginState.value = loginState.value.copy(errorState = LoginErrorState())
                true
            }
        }
    }


    private fun getLoginStatus() {
        disposable = repository.login()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(/* onSuccess = */ { onSuccess ->
                if (onSuccess.IsSuccess) {
                    // TODO Trigger login in authentication flow
                    val user =
                        User(emailId = loginState.value.email, password = loginState.value.password)
                    sharedPreferences.user = user
                    loginState.value = loginState.value.copy(isLoginSuccessful = true)
//                    navController.navigate(Screen.HomeScreen.route)
                }

            },
                /* onError = */ { errorData ->
                    error.postValue(errorData.toString())
                })
    }
}