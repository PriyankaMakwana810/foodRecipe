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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.tridya.foodrecipeblog.api.ApiState
import com.tridya.foodrecipeblog.api.response.Meal
import com.tridya.foodrecipeblog.components.ItemRecipeCard
import com.tridya.foodrecipeblog.components.ListNewRecipe
import com.tridya.foodrecipeblog.components.ListSelectCountry
import com.tridya.foodrecipeblog.components.ProfileSection
import com.tridya.foodrecipeblog.components.SearchBarWithFilter
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.components.showProgress
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

//    val countriesState by homeViewModel.countries
    val areaState by homeViewModel.areas
    val userName = homeViewModel.sharedPreferences.user?.userName
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        homeViewModel.getAreas()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), color = white
    ) {
        when (areaState) {
            is ApiState.Loading -> {
                showProgress()
//                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is ApiState.Success -> {
//                val countries = (countriesState as ApiState.Success<List<String>>)
                var selectedArea by remember {
                    mutableStateOf("")
                }
                val areas = (areaState as ApiState.Success<List<Meal>>).data
                val listOfArea: List<String> = areas.map { meal ->
                    meal.strArea
                }
                selectedArea = listOfArea.first()
                Column(
                    Modifier
                        .verticalScroll(state = scrollState)
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    ProfileSection(modifier = Modifier.fillMaxWidth(), userName = "Hello $userName")
                    SearchBarWithFilter(ifHome = true, onSearchClicked = {
                        navController.navigate(Screen.SearchScreen.route) {
                            this.launchSingleTop = true
                        }
                    })
                    ListSelectCountry(listOfCountries = listOfArea) { selectedItem ->
                        println("Selected item: $selectedItem")
                        selectedArea = selectedItem
                    }
                    LazyRow(
                        modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 15.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                    ) {
                        items(recipesByCountry) { item: RecipeCard ->
                            ItemRecipeCard(recipe = item) {
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

            is ApiState.Error -> {
                val error = (areaState as ApiState.Error).message
                // Show error message
                Text(
                    text = "Failed to fetch data: $error",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }


    }
}


@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController(), paddingValues = PaddingValues())
}
