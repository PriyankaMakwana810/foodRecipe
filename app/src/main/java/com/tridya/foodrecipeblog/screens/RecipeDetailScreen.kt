package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.CustomTabs
import com.tridya.foodrecipeblog.components.ProfileSectionOfRecipe
import com.tridya.foodrecipeblog.components.RecipesItemsComponent
import com.tridya.foodrecipeblog.components.ShowIngredients
import com.tridya.foodrecipeblog.components.ShowProcedure
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.models.recipesByCountry
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun RecipeDetailScreen(navController: NavController, paddingValues: PaddingValues, recipeId: Int) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        var showProcedure by remember { mutableStateOf(false) }
        var expanded by remember { mutableStateOf(false) }
        val recipe = recipesByCountry[recipeId]

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
                    DropdownMenuItem(text = { Text(text = "Share") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.v_ic_menu_share),
                                contentDescription = "Share"
                            )
                        },
                        onClick = {
                            expanded = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Rate Recipe") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.v_ic_menu_star),
                                contentDescription = "Rate"
                            )
                        },
                        onClick = { expanded = false })
                    DropdownMenuItem(
                        text = { Text(text = "Review") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.v_ic_menu_review),
                                contentDescription = "Review"
                            )
                        },
                        onClick = { expanded = false })
                    DropdownMenuItem(
                        text = { Text(text = "Unsave") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.v_ic_menu_unsave),
                                contentDescription = "Unsave"
                            )
                        },
                        onClick = { expanded = false })
                }
            }

            RecipesItemsComponent(
                modifier = Modifier.padding(horizontal = 10.dp),
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
                    text = recipe.name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = black,
                    ),
                    modifier = Modifier.width(190.dp)
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
            CustomTabs(onIngridentClicked = {
                showProcedure = false
            }, onProcedureClicked = {
                showProcedure = true
            })
            if (showProcedure) {
                ShowProcedure()
            } else {
                ShowIngredients()
            }
        }
    }
}


@Preview
@Composable
fun PreviewRecipeDetailScreen() {
    RecipeDetailScreen(
        navController = rememberNavController(),
        paddingValues = PaddingValues(),
        recipeId = 0
    )
}