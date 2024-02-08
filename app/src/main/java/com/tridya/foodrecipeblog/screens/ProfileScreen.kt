package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.background
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

    var expanded by remember { mutableStateOf(false) }
    val allPostedRecipes by profileViewModel.allPostedRecipes.collectAsState()

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
                                .size(300.dp),
                            placeholder = painterResource(id = R.drawable.nothing_found),
                            contentDescription = "You haven't Posted any Recipe yet!",
                            contentScale = ContentScale.Fit,
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        NormalTextComponent(
                            modifier = Modifier,
                            value = "You haven't Posted any Recipe yet!",
                            fontSize = 20.sp,
                            align = TextAlign.Center
                        )
                    }
                } else {
                    LazyColumn {
                        items(allPostedRecipes) { item ->
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
}

@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(
        navController = rememberNavController(),
        paddingValues = PaddingValues(),
    )
}