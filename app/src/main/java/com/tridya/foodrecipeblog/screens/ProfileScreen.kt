package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.CustomProfileTabs
import com.tridya.foodrecipeblog.components.RecipesItemsComponent
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.components.UserProfileSectionUI
import com.tridya.foodrecipeblog.models.recipesByCountry
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.viewModels.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {

    var expanded by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ToolbarComponent(toolbarTitle = stringResource(R.string.profile),
                showBackArrow = false,
                showMenuIcon = true,
                onMenuClicked = {

                })
            DropdownMenu(
                modifier = Modifier.background(white),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                DropdownMenuItem(text = { Text(text = "Share") }, leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_logout_24),
                        contentDescription = "Logout"
                    )
                }, onClick = {
                    expanded = false
                    profileViewModel.sharedPreferences.logout()
                    navController.navigate(Screen.IntroScreen.route)
                })
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                UserProfileSectionUI()
                CustomProfileTabs()
                LazyColumn {
                    items(recipesByCountry.filter { it.isSaved }) { item ->
                        RecipesItemsComponent(recipe = item,
                            isFromSaved = true,
                            onRecipeItemClicked = {
                                navController.navigate(Screen.RecipeDetailScreen.route + "/${item.idMeal}") {
                                    this.launchSingleTop = true
                                }
                            })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(
        navController = rememberNavController(),
        paddingValues = PaddingValues(),
    )
}