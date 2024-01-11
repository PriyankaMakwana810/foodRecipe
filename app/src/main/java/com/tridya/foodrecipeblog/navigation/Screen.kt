package com.tridya.foodrecipeblog.navigation

import com.tridya.foodrecipeblog.utils.Constants.HOME
import com.tridya.foodrecipeblog.utils.Constants.INTRO
import com.tridya.foodrecipeblog.utils.Constants.LOGIN
import com.tridya.foodrecipeblog.utils.Constants.REGISTER

sealed class Screen(val route: String) {
    object IntroScreen: Screen(INTRO)
    object LoginScreen: Screen(LOGIN)
    object RegisterScreen: Screen(REGISTER)
    object HomeScreen: Screen(HOME)
}