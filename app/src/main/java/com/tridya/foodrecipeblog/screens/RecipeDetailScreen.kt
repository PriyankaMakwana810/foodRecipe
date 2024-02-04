package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.api.ApiState
import com.tridya.foodrecipeblog.api.response.RecipeCard
import com.tridya.foodrecipeblog.components.CustomRecipeDetailsTabs
import com.tridya.foodrecipeblog.components.NormalTextComponent
import com.tridya.foodrecipeblog.components.ProfileSectionOfRecipe
import com.tridya.foodrecipeblog.components.RateDialogComponent
import com.tridya.foodrecipeblog.components.RecipesItemsComponent
import com.tridya.foodrecipeblog.components.ShareDialogComponent
import com.tridya.foodrecipeblog.components.ShowIngredients
import com.tridya.foodrecipeblog.components.ShowProcedure
import com.tridya.foodrecipeblog.components.ShowProgress
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.viewModels.RecipeDetailsViewModel

@Composable
fun RecipeDetailScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    recipeId: String,
    recipeDetailsViewModel: RecipeDetailsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val stateRecipeDetails by recipeDetailsViewModel.recipeDetails.collectAsState()
    val recipeDetailsFromDb by recipeDetailsViewModel.selectedRecipe.collectAsState()
    var recipe: RecipeCard

    LaunchedEffect(true) {
        recipeDetailsViewModel.getRecipeDetailsByID(recipeId)
        if (recipeDetailsFromDb != null) {
            recipe = recipeDetailsFromDb as RecipeCard
        } else {
            recipeDetailsViewModel.getRecipeDetailsByID(recipeId)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        var showProcedure by remember { mutableStateOf(false) }
        var expanded by remember { mutableStateOf(false) }
        var openShareDialog by remember { mutableStateOf(false) }
        var openRatingDialog by remember { mutableStateOf(false) }

        when (stateRecipeDetails) {
            is ApiState.Loading -> {
                ShowProgress()
            }

            is ApiState.Success -> {
                recipe = (stateRecipeDetails as ApiState.Success<RecipeCard>).data
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    ToolbarComponent(toolbarTitle = "", showMenuIcon = true, onMenuClicked = {
                        expanded = true
                    }, onBackClicked = {
                        navController.navigateUp()
                    })
                    Box {
                        DropdownMenu(
                            modifier = Modifier.background(white),
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            offset = DpOffset(x = (-66).dp, y = (-10).dp)
                        ) {
                            DropdownMenuItem(text = { Text(text = "Share") }, leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.v_ic_menu_share),
                                    contentDescription = "Share"
                                )
                            }, onClick = {
                                expanded = false
                                openShareDialog = true
                            })

                            DropdownMenuItem(text = { Text(text = "Rate Recipe") }, leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.v_ic_menu_star),
                                    contentDescription = "Rate"
                                )
                            }, onClick = {
                                expanded = false
                                openRatingDialog = true
                            })
                            DropdownMenuItem(text = { Text(text = "Review") }, leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.v_ic_menu_review),
                                    contentDescription = "Review"
                                )
                            }, onClick = { expanded = false })
                            DropdownMenuItem(text = { Text(text = "Unsave") }, leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.v_ic_menu_unsave),
                                    contentDescription = "Unsave"
                                )
                            }, onClick = { expanded = false })
                        }
                    }

                    RecipesItemsComponent(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .heightIn(200.dp),
                        recipe = recipe,
                        isFromDetail = true,
                        isFromSaved = true
                    )
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = recipe.strMeal, style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight(600),
                                color = black,
                            ), modifier = Modifier.width(190.dp)
                        )
                        Text(
                            text = "(13k Reviews)",
                            // Poppins/label/regular
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400),
                                color = gray3,
                            )
                        )
                    }
                    ProfileSectionOfRecipe(recipe = recipe)
                    CustomRecipeDetailsTabs(onIngredientClicked = {
                        showProcedure = false
                    }, onProcedureClicked = {
                        showProcedure = true
                    })
                    if (showProcedure) {
                        recipe.strInstructions?.let { ShowProcedure(procedure = it) }
                    } else {
                        ShowIngredients(recipe = recipe)
                    }
                    if (openShareDialog) {
                        ShareDialogComponent(
                            link = if (recipe.strSource.isNullOrEmpty() && recipe.strSource == "") "" else "",
                            onButtonClicked = {
                                openShareDialog = !openShareDialog
                                recipeDetailsViewModel.shareRecipeLink(
                                    context.getString(R.string.app_name),
                                    context.getString(R.string.i_found_this_amazing_recipe_try_this_out),
                                    context = context
                                )
                            })
                    }
                    if (openRatingDialog) {
                        RateDialogComponent(onButtonClicked = {
                            openRatingDialog = !openRatingDialog
                        })
                    }
                }
            }

            is ApiState.Error -> {
                val error = (stateRecipeDetails as ApiState.Error).message
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NormalTextComponent(value = error, fontSize = 20.sp, align = TextAlign.Center)
                }
            }
        }

    }
}

@Preview
@Composable
fun PreviewRecipeDetailScreen() {
    RecipeDetailScreen(
        navController = rememberNavController(), paddingValues = PaddingValues(), recipeId = ""
    )
}