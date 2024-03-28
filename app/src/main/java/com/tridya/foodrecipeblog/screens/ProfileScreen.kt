package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.CommonDialogComponent
import com.tridya.foodrecipeblog.components.CustomProfileTabs
import com.tridya.foodrecipeblog.components.NormalTextComponent
import com.tridya.foodrecipeblog.components.RecipesItemsComponent
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.components.UserProfileSectionUI
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.viewModels.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val user = profileViewModel.sharedPreferences.user
    var expanded by remember { mutableStateOf(false) }
    val allPostedRecipes by profileViewModel.allPostedRecipes.collectAsState()
    var openDialog by remember { mutableStateOf(false) }

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
                    expanded = true
                })
            Box {
                DropdownMenu(
                    modifier = Modifier.background(white),
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    offset = DpOffset(x = (-66).dp, y = (-10).dp)

                ) {
                    DropdownMenuItem(text = { Text(text = stringResource(R.string.logout)) },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_logout_24),
                                contentDescription = stringResource(R.string.logout)
                            )
                        },
                        onClick = {
                            expanded = false
                            openDialog = true
                        })
                }
            }

            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                if (openDialog) {
                    CommonDialogComponent(title = stringResource(R.string.logout),
                        subTitle = stringResource(R.string.after_logout_you_want_to_able_to_see_recipe_added_by_you_are_you_sure_you_want_to_logout),
                        onButtonClicked = {
                            profileViewModel.sharedPreferences.logout()
                            navController.navigate(Screen.IntroScreen.route) {
                                popUpTo(0) {
                                    inclusive = true
                                }
                            }
                        },
                        onDismissClicked = {
                            openDialog = false
                        })
                }
                if (user != null) {
                    UserProfileSectionUI(
                        userData = user, recipePostedCount = allPostedRecipes.size
                    )
                }
                CustomProfileTabs()
                if (allPostedRecipes.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(R.drawable.nothing_found)
                                .memoryCachePolicy(CachePolicy.ENABLED)
                                .diskCachePolicy(CachePolicy.ENABLED).crossfade(true).build(),
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .size(150.dp),
                            placeholder = painterResource(id = R.drawable.nothing_found),
                            contentDescription = stringResource(R.string.you_haven_t_posted_any_recipe_yet),
                            contentScale = ContentScale.Fit,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        NormalTextComponent(
                            modifier = Modifier,
                            value = stringResource(R.string.you_haven_t_posted_any_recipe_yet),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            align = TextAlign.Center
                        )
                    }
                } else {
                    if (openDialog) {
                        CommonDialogComponent(title = stringResource(R.string.logout),
                            subTitle = stringResource(R.string.after_logout_you_want_to_able_to_see_recipe_added_by_you_are_you_sure_you_want_to_logout),
                            onButtonClicked = {
                                profileViewModel.sharedPreferences.logout()
                                navController.navigate(Screen.IntroScreen.route) {
                                    popUpTo(0) {
                                        inclusive = true
                                    }
                                }
                            },
                            onDismissClicked = {
                                openDialog = false
                            })
                    }
                    LazyColumn {
                        items(allPostedRecipes) { item ->
                            RecipesItemsComponent(
                                recipe = item, isFromSaved = true, onRecipeItemClicked = {
                                    navController.navigate(Screen.RecipeDetailScreen.route + "/${item.idMeal}") {
                                        this.launchSingleTop = true
                                    }
                                }, padding = 5.dp, horizontalPadding = 0.dp
                            )
                        }
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