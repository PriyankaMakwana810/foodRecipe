package com.tridya.foodrecipeblog.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.tridya.foodrecipeblog.api.repo.LoginRepository
import com.tridya.foodrecipeblog.models.ErrorState
import com.tridya.foodrecipeblog.models.User
import com.tridya.foodrecipeblog.screens.login.state.LoginErrorState
import com.tridya.foodrecipeblog.screens.login.state.LoginState
import com.tridya.foodrecipeblog.screens.login.state.LoginUiEvent
import com.tridya.foodrecipeblog.screens.login.state.emailEmptyErrorState
import com.tridya.foodrecipeblog.screens.login.state.passwordEmptyErrorState
import com.tridya.foodrecipeblog.utils.Constants.SHARED_COMMON
import com.tridya.foodrecipeblog.utils.PrefUtils
import com.tridya.foodrecipeblog.utils.showShortToast
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    @Named(SHARED_COMMON) val sharedPreferences: PrefUtils,
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
        val emailString = loginState.value.email.trim()
        val passwordString = loginState.value.password
        return when {

            // Email/Mobile empty
            emailString.isEmpty() -> {
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
                    val user =
                        User(
                            userName = "priyanka",
                            emailId = loginState.value.email,
                            password = loginState.value.password,
                            profilePicPath = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                        )
                    sharedPreferences.user = user
                    loginState.value = loginState.value.copy(isLoginSuccessful = true)
//                    navController.navigate(Screen.HomeScreen.route)
                }
            },
                /* onError = */ { errorData ->
                    error.postValue(errorData.toString())
                })
    }
    fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                val user =
                    User(
                        userId = account.id,
                        userName = account.displayName,
                        emailId = account.id,
                        profilePicPath = if (account.photoUrl == null) account.photoUrl?.path else "https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
//                        profilePicPath = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                    )
                sharedPreferences.user = user
//                addDetails(account)
                Log.e("TAG", "handleResult: $account", )
                loginState.value = loginState.value.copy(isLoginSuccessful = true)

            }
        } catch (e: ApiException) {
            Log.e("TAG", "handleResult:$e ", )

//            showShortToast(requireContext(), e.toString())
        }
    }
}