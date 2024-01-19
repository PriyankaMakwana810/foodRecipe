package com.tridya.foodrecipeblog.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tridya.foodrecipeblog.screens.HomeScreen
import com.tridya.foodrecipeblog.screens.IntroScreen
import com.tridya.foodrecipeblog.screens.NotificationScreen
import com.tridya.foodrecipeblog.screens.ProfileScreen
import com.tridya.foodrecipeblog.screens.SavedScreen
import com.tridya.foodrecipeblog.screens.SearchScreen
import com.tridya.foodrecipeblog.screens.login.LoginScreen
import com.tridya.foodrecipeblog.screens.register.RegisterScreen

@Composable
fun NavigationCompose(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(navController = navController, startDestination = Screen.IntroScreen.route) {
        composable(route = Screen.IntroScreen.route) {
            IntroScreen(navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController, paddingValues)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController, paddingValues)
        }
        composable(route = Screen.SavedScreen.route) {
            SavedScreen(navController = navController)
        }
        composable(route = Screen.NotificationScreen.route) {
            NotificationScreen()
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen()
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
    }
}