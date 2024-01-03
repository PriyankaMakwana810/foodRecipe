package com.tridya.foodrecipeblog.navigation

sealed class Screen(val route: String) {
    object IntroScreen: Screen("intro_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
}