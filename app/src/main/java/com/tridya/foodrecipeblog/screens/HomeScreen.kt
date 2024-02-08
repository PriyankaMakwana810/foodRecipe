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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.tridya.foodrecipeblog.api.ApiState
import com.tridya.foodrecipeblog.api.response.Areas
import com.tridya.foodrecipeblog.api.response.ResponseOfRecipes
import com.tridya.foodrecipeblog.components.ItemNewRecipe
import com.tridya.foodrecipeblog.components.ItemRecipeCard
import com.tridya.foodrecipeblog.components.ListSelectCountry
import com.tridya.foodrecipeblog.components.NormalTextComponent
import com.tridya.foodrecipeblog.components.ProfileSection
import com.tridya.foodrecipeblog.components.SearchBarWithFilter
import com.tridya.foodrecipeblog.components.ShowProgress
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.toEntity
import com.tridya.foodrecipeblog.viewModels.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()

    val areaState by homeViewModel.areas.collectAsState()
    val recipeByArea by homeViewModel.recipesByArea.collectAsState()
    val newRecipes by homeViewModel.newRecipes.collectAsState()

    val userName = homeViewModel.sharedPreferences.user?.userName

    val recipeScrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        if (homeViewModel.loaded.value) {
            return@LaunchedEffect
        } else {
            homeViewModel.getAreas()
            homeViewModel.getNewRecipe()
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), color = white
    ) {
        when (areaState) {
            is ApiState.Loading -> {
//                ShowProgress()
            }

            is ApiState.Success -> {
                val areas = (areaState as ApiState.Success<List<Areas>>).data
                val listOfArea: List<String> = areas.map { meal -> meal.strArea }
                var selectedArea by remember {
                    mutableStateOf("")
                }
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
                    LaunchedEffect(Unit) {
                        selectedArea = listOfArea.first()
                        if (!homeViewModel.loaded.value)
                            homeViewModel.getRecipeByArea(listOfArea.first())
                    }
                    ListSelectCountry(listOfCountries = listOfArea) { selectedItem ->
                        println("Selected item: $selectedItem")
                        selectedArea = selectedItem
                        coroutineScope.launch {
                            recipeScrollState.animateScrollToItem(index = 0)
                        }
                        homeViewModel.getRecipeByArea(selectedArea)
                    }
                    when (recipeByArea) {
                        is ApiState.Loading -> {
                            ShowProgress()
                        }

                        is ApiState.Success -> {
                            val recipeByCountry =
                                (recipeByArea as ApiState.Success<List<ResponseOfRecipes>>).data.map {
                                    it.toEntity(
                                        area = selectedArea
                                    )
                                }
                            LazyRow(
                                state = recipeScrollState,
                                modifier = Modifier.padding(
                                    start = 15.dp,
                                    top = 10.dp,
                                    bottom = 10.dp
                                ),
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                            ) {
                                items(recipeByCountry) { item: RecipeCard ->
                                    ItemRecipeCard(recipe = item) {
                                        navController.navigate(Screen.RecipeDetailScreen.route + "/${item.idMeal}") {
                                            this.launchSingleTop = true
                                        }
                                    }
                                }
                            }
                        }

                        is ApiState.Error -> {
                            val error = (recipeByArea as ApiState.Error).message
                            NormalTextComponent(
                                value = error,
                                fontSize = 20.sp,
                                align = TextAlign.Center
                            )
                        }
                    }

                    SimpleTextComponent(
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp),
                        value = stringResource(R.string.new_recipes),
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        textColor = black,
                        textAlign = TextAlign.Left
                    )
                    when (newRecipes) {
                        is ApiState.Loading -> {
                            ShowProgress()
                        }

                        is ApiState.Success -> {
                            val listNewRecipes: List<RecipeCard> =
                                (newRecipes as ApiState.Success<List<ResponseOfRecipes>>).data.map { it.toEntity() }

                            LazyRow(
                                modifier = Modifier.padding(start = 15.dp),
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                            ) {
                                items(listNewRecipes) { item: RecipeCard ->
                                    ItemNewRecipe(recipe = item) {
                                        navController.navigate(Screen.RecipeDetailScreen.route + "/${item.idMeal}") {
                                            this.launchSingleTop = true
                                        }
                                    }
                                }
                            }
                        }

                        is ApiState.Error -> {
                            val error = (recipeByArea as ApiState.Error).message
                            LazyRow(
                                modifier = Modifier.padding(start = 15.dp),
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                            ) {
                                item {
                                    NormalTextComponent(
                                        value = error,
                                        fontSize = 20.sp,
                                        align = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }

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
