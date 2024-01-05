package com.tridya.foodrecipeblog.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tridya.foodrecipeblog.models.RegisterUser

class RegisterViewModel : ViewModel() {
    var registerUser = RegisterUser()

    var userName = mutableStateOf(registerUser.name)
    var isUserNameValid = mutableStateOf(false)
    var userNameErrMsg = mutableStateOf("")

    var email = mutableStateOf(registerUser.email)
    var isEmailValid = mutableStateOf(false)
    var emailErrMsg = mutableStateOf("")

    var password = mutableStateOf(registerUser.email)
    var isPasswordValid = mutableStateOf(false)
    var passwordErrMsg = mutableStateOf("")

    var cPassword = mutableStateOf(registerUser.email)
    var iscPasswordValid = mutableStateOf(false)
    var cPasswordErrMsg = mutableStateOf("")

    var isEnableRegButton = mutableStateOf(false)
    private fun shouldEnableRegistrationButton() {
        isEnableRegButton.value = userNameErrMsg.value.isEmpty()
                && emailErrMsg.value.isEmpty()
                && passwordErrMsg.value.isEmpty()
                && cPasswordErrMsg.value.isEmpty()
                && userName.value.isNotEmpty()
                && email.value.isNotEmpty()
                && password.value.isNotEmpty()
                && cPassword.value.isNotEmpty()
    }

    fun validateUserName() {
        if (userName.value.length >= 10) {
            isUserNameValid.value = true
            userNameErrMsg.value = "User Name should be less than 10 chars"
        } else {
            isUserNameValid.value = false
            userNameErrMsg.value = ""
        }
        shouldEnableRegistrationButton()
    }

    fun validateEmail() {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isEmailValid.value = true
            emailErrMsg.value = "Input proper email id"
        } else {
            isEmailValid.value = false
            emailErrMsg.value = ""
        }
        shouldEnableRegistrationButton()
    }

    fun validatePassword() {
        if (password.value != "123") {
            isPasswordValid.value = true
            passwordErrMsg.value = "Password should be 123"
        } else {
            isPasswordValid.value = false
            passwordErrMsg.value = ""
        }
        shouldEnableRegistrationButton()
    }

    fun validateConfirmPassword() {
        if (password.value != cPassword.value) {
            iscPasswordValid.value = true
            cPasswordErrMsg.value = "Password did not match"
        } else {
            iscPasswordValid.value = false
            cPasswordErrMsg.value = ""
        }
        shouldEnableRegistrationButton()
    }

    fun register() {
        registerUser.name = userName.value
        registerUser.email = email.value
        registerUser.password = password.value
        registerUser.confirmPassword = cPassword.value

        Log.d("username", userName.value)
        Log.d("email", email.value)
        Log.d("password", password.value)
        Log.d("confirmPassword", cPassword.value)
        Log.d("Data:", registerUser.toString())
    }
}