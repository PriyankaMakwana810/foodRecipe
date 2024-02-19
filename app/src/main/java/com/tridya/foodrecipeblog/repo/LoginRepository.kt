package com.tridya.foodrecipeblog.repo

import com.tridya.foodrecipeblog.api.ApiInterface
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiInterface: ApiInterface) {
    fun login() = apiInterface.login()
    fun register() = apiInterface.register()
}