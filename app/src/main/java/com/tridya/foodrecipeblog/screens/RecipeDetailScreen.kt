package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
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
import com.tridya.foodrecipeblog.components.CustomRecipeDetailsTabs
import com.tridya.foodrecipeblog.components.NormalTextComponent
import com.tridya.foodrecipeblog.components.ProfileSectionOfRecipe
import com.tridya.foodrecipeblog.components.RateDialogComponent
import com.tridya.foodrecipeblog.components.RecipesItemsComponent
import com.tridya.foodrecipeblog.components.ReusableDropdownMenu
import com.tridya.foodrecipeblog.components.ShareDialogComponent
import com.tridya.foodrecipeblog.components.ShowIngredients
import com.tridya.foodrecipeblog.components.ShowProcedure
import com.tridya.foodrecipeblog.components.ShowProgress
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.showShortToast
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

    LaunchedEffect(true) {
        recipeDetailsViewModel.getRecipeDetailsByID(recipeId)
    }

    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        var showProcedure by remember { mutableStateOf(false) }
        var expanded by remember { mutableStateOf(false) }
        var openShareDialog by remember { mutableStateOf(false) }
        var openRatingDialog by remember { mutableStateOf(false) }
        val clipboardManager = LocalClipboardManager.current

        when (stateRecipeDetails) {
            is ApiState.Loading -> {
                ShowProgress()
            }

            is ApiState.Success -> {
                val recipe = (stateRecipeDetails as ApiState.Success<RecipeCard>).data
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
                        ReusableDropdownMenu(
                            items = listOf(
                                Triple("Share", painterResource(id = R.drawable.v_ic_menu_share)) {
                                    openShareDialog = true
                                },
                                Triple(
                                    "Rate Recipe",
                                    painterResource(id = R.drawable.v_ic_menu_star)
                                ) {
                                    openRatingDialog = true
                                },
                                Triple(
                                    "Review",
                                    painterResource(id = R.drawable.v_ic_menu_review)
                                ) {
                                    navController.navigate(Screen.ReviewScreen.route)
                                },
                                if (recipe.isSaved)
                                    Triple(
                                        "Unsave",
                                        painterResource(id = R.drawable.v_ic_menu_unsave)
                                    ) {
                                        recipeDetailsViewModel.updateRecipeIsSaved(
                                            false,
                                            recipe.idMeal
                                        )
                                    }
                                else
                                    Triple(
                                        "Save",
                                        painterResource(id = R.drawable.v_ic_menu_unsave)
                                    ) {
                                        recipeDetailsViewModel.updateRecipeIsSaved(
                                            true,
                                            recipe.idMeal
                                        )
                                    }
                            ),
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        )
                    }

                    RecipesItemsComponent(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .height(170.dp),
                        recipe = recipe,
                        isFromDetail = true,
                        isFromSaved = true,
                        padding = 0.dp
                    )
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        NormalTextComponent(
                            modifier = Modifier.width(190.dp),
                            value = recipe.strMeal,
                            fontSize = 18.sp,
                            fontWeight = FontWeight(600)
                        )

                        NormalTextComponent(
                            value = stringResource(R.string._13k_reviews),
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400),
                            textColor = gray3
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
                            link = if (recipe.strSource.isNullOrEmpty() && recipe.strSource == "") "" else recipe.strSource!!,
                            onButtonClicked = {
                                openShareDialog = !openShareDialog
                                clipboardManager.setText(AnnotatedString(recipe.strSource.toString()))
                                recipeDetailsViewModel.shareRecipeLink(
                                    context.getString(R.string.i_found_this_amazing_recipe_try_this_out),
                                    recipe.strSource.toString(),
                                    context = context
                                )
                            })
                    }
                    if (openRatingDialog) {
                        RateDialogComponent(onButtonClicked = {
                            openRatingDialog = !openRatingDialog
                            showShortToast(
                                context,
                                context.getString(R.string.thanks_for_rating_this_recipe)
                            )
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