package com.tridya.foodrecipeblog.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tridya.foodrecipeblog.api.repo.LoginRepository
import com.tridya.foodrecipeblog.models.ErrorState
import com.tridya.foodrecipeblog.models.User
import com.tridya.foodrecipeblog.screens.register.state.RegistrationErrorState
import com.tridya.foodrecipeblog.screens.register.state.RegistrationState
import com.tridya.foodrecipeblog.screens.register.state.RegistrationUiEvent
import com.tridya.foodrecipeblog.screens.register.state.confirmPasswordEmptyErrorState
import com.tridya.foodrecipeblog.screens.register.state.emailEmptyErrorState
import com.tridya.foodrecipeblog.screens.register.state.nameEmptyErrorState
import com.tridya.foodrecipeblog.screens.register.state.passwordEmptyErrorState
import com.tridya.foodrecipeblog.screens.register.state.passwordMismatchErrorState
import com.tridya.foodrecipeblog.utils.Constants
import com.tridya.foodrecipeblog.utils.PrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: LoginRepository,
    @Named(Constants.SHARED_COMMON) val sharedPreferences: PrefUtils,
) : ViewModel() {
    var registrationState = mutableStateOf(RegistrationState())
        private set

    val error = MutableLiveData<String>()
    private lateinit var disposable: Disposable
    fun onUiEvent(registrationUiEvent: RegistrationUiEvent) {
        when (registrationUiEvent) {

            is RegistrationUiEvent.NameChanged -> {
                registrationState.value = registrationState.value.copy(
                    name = registrationUiEvent.inputValue,
                    errorState = registrationState.value.errorState.copy(
                        nameErrorState = if (registrationUiEvent.inputValue.trim()
                                .isEmpty()
                        ) {
                            // Mobile Number Empty state
                            nameEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }

                    )
                )
            }
            is RegistrationUiEvent.EmailChanged -> {
                registrationState.value = registrationState.value.copy(
                    emailId = registrationUiEvent.inputValue,
                    errorState = registrationState.value.errorState.copy(
                        emailIdErrorState = if (registrationUiEvent.inputValue.trim()
                                .isEmpty()
                        ) {
                            // Email id empty state
                            emailEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }

                    )
                )
            }

            is RegistrationUiEvent.PasswordChanged -> {
                registrationState.value = registrationState.value.copy(
                    password = registrationUiEvent.inputValue,
                    errorState = registrationState.value.errorState.copy(
                        passwordErrorState = if (registrationUiEvent.inputValue.trim()
                                .isEmpty()
                        ) {
                            // Password Empty state
                            passwordEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }

                    )
                )
            }

            is RegistrationUiEvent.ConfirmPasswordChanged -> {
                registrationState.value = registrationState.value.copy(
                    confirmPassword = registrationUiEvent.inputValue,
                    errorState = registrationState.value.errorState.copy(
                        confirmPasswordErrorState = when {

                            // Empty state of confirm password
                            registrationUiEvent.inputValue.trim().isEmpty() -> {
                                confirmPasswordEmptyErrorState
                            }

                            // Password is different than the confirm password
                            registrationState.value.password.trim() != registrationUiEvent.inputValue -> {
                                passwordMismatchErrorState
                            }

                            // Valid state
                            else -> ErrorState()
                        }
                    )
                )
            }

            is RegistrationUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    getRegisterStatus()
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val emailString = registrationState.value.emailId.trim()
        val mobileNumberString = registrationState.value.name.trim()
        val passwordString = registrationState.value.password.trim()
        val confirmPasswordString = registrationState.value.confirmPassword.trim()

        return when {

            // Email empty
            emailString.isEmpty() -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(
                        emailIdErrorState = emailEmptyErrorState
                    )
                )
                false
            }

            //Mobile Number Empty
            mobileNumberString.isEmpty() -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(
                        nameErrorState = nameEmptyErrorState
                    )
                )
                false
            }

            //Password Empty
            passwordString.isEmpty() -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(
                        passwordErrorState = passwordEmptyErrorState
                    )
                )
                false
            }

            //Confirm Password Empty
            confirmPasswordString.isEmpty() -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(
                        confirmPasswordErrorState = confirmPasswordEmptyErrorState
                    )
                )
                false
            }

            // Password and Confirm Password are different
            passwordString != confirmPasswordString -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(
                        confirmPasswordErrorState = passwordMismatchErrorState
                    )
                )
                false
            }

            // No errors
            else -> {
                // Set default error state
                registrationState.value =
                    registrationState.value.copy(errorState = RegistrationErrorState())
                true
            }
        }
    }

    fun getRegisterStatus() {
        disposable = repository.register()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(/* onSuccess = */ { onSuccess ->
                if (onSuccess.IsSuccess) {
//                    navController.navigate(Screen.HomeScreen.route)
                    val user =
                        User(userName = registrationState.value.name, emailId = registrationState.value.emailId, password = registrationState.value.password)
                    sharedPreferences.user = user
                    registrationState.value =
                        registrationState.value.copy(isRegistrationSuccessful = true)
                }
            },/* onError = */ { errorData ->
                error.postValue(errorData.toString())
            })
    }
}