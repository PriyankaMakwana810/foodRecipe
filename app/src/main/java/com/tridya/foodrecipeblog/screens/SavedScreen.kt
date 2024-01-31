package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.components.RecipesItemsComponent
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.models.recipesByCountry
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun SavedScreen(navController: NavController, paddingValues: PaddingValues) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ToolbarComponent(showBackArrow = false, toolbarTitle = "Saved Recipes")
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
            ) {
                items(recipesByCountry.filter { it.isSaved }) { item ->
                    RecipesItemsComponent(recipe = item, isFromSaved = true, onRecipeItemClicked = {
                        navController.navigate(Screen.RecipeDetailScreen.route + "/${item.idMeal}"){
                            this.launchSingleTop = true
                        }
                    })
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewSavedScreen() {
    SavedScreen(navController = rememberNavController(), paddingValues = PaddingValues())
}
