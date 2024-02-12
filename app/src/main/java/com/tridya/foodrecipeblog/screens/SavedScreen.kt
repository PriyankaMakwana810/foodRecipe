package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.NormalTextComponent
import com.tridya.foodrecipeblog.components.RecipesItemsComponent
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.viewModels.CommonViewModel

@Composable
fun SavedScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    savedViewModel: CommonViewModel = hiltViewModel(),
) {

    val allSavedRecipe by savedViewModel.allSavedRecipes.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ToolbarComponent(showBackArrow = false, toolbarTitle = "Saved Recipes")
            if (allSavedRecipe.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(R.drawable.nothing_found).memoryCachePolicy(CachePolicy.ENABLED)
                            .diskCachePolicy(CachePolicy.ENABLED).crossfade(true).build(),
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .size(250.dp),
                        placeholder = painterResource(id = R.drawable.nothing_found),
                        contentDescription = stringResource(R.string.you_haven_t_saved_any_recipe_yet),
                        contentScale = ContentScale.Fit,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    NormalTextComponent(
                        value = stringResource(R.string.you_haven_t_saved_any_recipe_yet),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        align = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                ) {
                    items(allSavedRecipe) { item ->
                        RecipesItemsComponent(
                            recipe = item,
                            isFromSaved = true,
                            onRecipeItemClicked = {
                                navController.navigate(Screen.RecipeDetailScreen.route + "/${item.idMeal}") {
                                    this.launchSingleTop = true
                                }
                            },
                            padding = 10.dp
                        )
                    }
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
