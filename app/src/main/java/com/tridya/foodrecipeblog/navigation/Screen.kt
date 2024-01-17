package com.tridya.foodrecipeblog.navigation

import com.tridya.foodrecipeblog.utils.Constants.HOME
import com.tridya.foodrecipeblog.utils.Constants.INTRO
import com.tridya.foodrecipeblog.utils.Constants.LOGIN
import com.tridya.foodrecipeblog.utils.Constants.NOTIFICATION
import com.tridya.foodrecipeblog.utils.Constants.PROFILE
import com.tridya.foodrecipeblog.utils.Constants.REGISTER
import com.tridya.foodrecipeblog.utils.Constants.SAVED

sealed class Screen(val route: String) {
    data object IntroScreen : Screen(INTRO)
    data object LoginScreen : Screen(LOGIN)
    data object RegisterScreen : Screen(REGISTER)
    data object HomeScreen : Screen(HOME)
    data object SavedScreen : Screen(SAVED)
    data object NotificationScreen : Screen(NOTIFICATION)
    data object ProfileScreen : Screen(PROFILE)
}