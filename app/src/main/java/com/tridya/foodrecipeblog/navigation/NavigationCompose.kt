package com.tridya.foodrecipeblog.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tridya.foodrecipeblog.screens.HomeScreen
import com.tridya.foodrecipeblog.screens.IntroScreen
import com.tridya.foodrecipeblog.screens.NewRecipeScreen
import com.tridya.foodrecipeblog.screens.NotificationScreen
import com.tridya.foodrecipeblog.screens.ProfileScreen
import com.tridya.foodrecipeblog.screens.RecipeDetailScreen
import com.tridya.foodrecipeblog.screens.ReviewScreen
import com.tridya.foodrecipeblog.screens.SavedScreen
import com.tridya.foodrecipeblog.screens.SearchScreen
import com.tridya.foodrecipeblog.screens.login.LoginScreen
import com.tridya.foodrecipeblog.screens.register.RegisterScreen

@Composable
fun NavigationCompose(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(navController = navController, startDestination = Screen.IntroScreen.route,
        enterTransition = { fadeIn(animationSpec = tween(400)) },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -300 }, animationSpec = tween(
                    durationMillis = 300, easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -300 }, animationSpec = tween(
                    durationMillis = 300, easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = { fadeOut(animationSpec = tween(400)) }) {
        composable(route = Screen.IntroScreen.route) {
            IntroScreen(navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController, paddingValues)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController, paddingValues)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController, paddingValues)
        }
        composable(route = Screen.NewRecipeScreen.route) {
            NewRecipeScreen(navController = navController, paddingValues)
        }
        composable(
            route = Screen.RecipeDetailScreen.route + "/{recipeId}", arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.StringType
                })
        ) { entry ->
            entry.arguments?.getString("recipeId")?.let {
                RecipeDetailScreen(
                    navController = navController,
                    paddingValues = paddingValues,
                    recipeId = it
                )
            }

        }
        composable(route = Screen.ReviewScreen.route) {
            ReviewScreen(navController = navController, paddingValues = paddingValues)
        }
        composable(route = Screen.SavedScreen.route) {
            SavedScreen(navController = navController, paddingValues)
        }
        composable(route = Screen.NotificationScreen.route) {
            NotificationScreen(navController, paddingValues)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController, paddingValues)
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController, paddingValues)
        }
    }
}