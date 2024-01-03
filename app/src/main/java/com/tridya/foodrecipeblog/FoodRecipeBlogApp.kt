package com.tridya.foodrecipeblog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.navigation.NavigationCompose
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun FoodRecipeBlogApp(){
    Surface(
        color = white,
        modifier = Modifier.fillMaxSize()
    ) {
//        IntroScreen
        val navController = rememberNavController()
        NavigationCompose(navController)
    }
}