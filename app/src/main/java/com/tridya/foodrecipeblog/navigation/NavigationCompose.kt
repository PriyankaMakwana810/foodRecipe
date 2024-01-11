package com.tridya.foodrecipeblog.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tridya.foodrecipeblog.screens.HomeScreen
import com.tridya.foodrecipeblog.screens.IntroScreen
import com.tridya.foodrecipeblog.screens.login.LoginScreen
import com.tridya.foodrecipeblog.screens.register.RegisterScreen
import com.tridya.foodrecipeblog.viewModels.LoginViewModel

@Composable
fun NavigationCompose(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.IntroScreen.route) {
        composable(route = Screen.IntroScreen.route){
            IntroScreen(navController)
        }
        composable(route = Screen.LoginScreen.route){
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(navController, loginViewModel)
        }
        composable(route = Screen.RegisterScreen.route){
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
    }
}