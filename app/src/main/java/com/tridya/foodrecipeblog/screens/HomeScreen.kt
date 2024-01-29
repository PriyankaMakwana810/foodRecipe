package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.ItemRecipeCard
import com.tridya.foodrecipeblog.components.ListNewRecipe
import com.tridya.foodrecipeblog.components.ListSelectCountry
import com.tridya.foodrecipeblog.components.ProfileSection
import com.tridya.foodrecipeblog.components.SearchBarWithFilter
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.models.RecipeCard
import com.tridya.foodrecipeblog.models.recipesByCountry
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.viewModels.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()

    val userName = homeViewModel.sharedPreferences.user?.userName
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), color = white
    ) {
        Column(
            Modifier.verticalScroll(state = scrollState)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            ProfileSection(modifier = Modifier.fillMaxWidth(), userName = "Hello $userName")
            SearchBarWithFilter(ifHome = true, onSearchClicked = {
                navController.navigate(Screen.SearchScreen.route) {
                    this.launchSingleTop = true
                }
            })
            ListSelectCountry { selectedItem ->
                println("Selected item: $selectedItem")
            }
//            ListPopularRecipeByCountry()
            LazyRow(
                modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                items(recipesByCountry) { item: RecipeCard ->
                    ItemRecipeCard(recipe = item) {
//                        navController.navigate(Screen.RecipeDetailScreen.route)
                        navController.navigate(Screen.RecipeDetailScreen.route + "/${item.id}") {
                            this.launchSingleTop = true
                        }
                    }
                }
            }
            SimpleTextComponent(
                modifier = Modifier.padding(horizontal = 20.dp),
                value = stringResource(R.string.new_recipes),
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                textColor = black,
                textAlign = TextAlign.Left
            )
            ListNewRecipe()
        }

    }
}


@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController(), paddingValues = PaddingValues())
}
