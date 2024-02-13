package com.tridya.foodrecipeblog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.models.BottomNavigationItem
import com.tridya.foodrecipeblog.navigation.NavigationCompose
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.ui.theme.primary100
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.Constants.HOME
import com.tridya.foodrecipeblog.utils.Constants.NOTIFICATION
import com.tridya.foodrecipeblog.utils.Constants.PROFILE
import com.tridya.foodrecipeblog.utils.Constants.SAVED

@Composable
fun FoodRecipeBlogApp() {

    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    var shouldShowBottomBar by remember {
        mutableStateOf(false)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    shouldShowBottomBar = when (navBackStackEntry?.destination?.route) {
        HOME, SAVED, NOTIFICATION, PROFILE -> true // on this screen bottom bar should be hidden
        else -> false // in all other cases show bottom bar
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (shouldShowBottomBar) {
            BottomAppBar(
                containerColor = white,
            ) {
                val selectedItem = remember { mutableIntStateOf(0) }
                BottomNavigationItem().bottomNavigationItems()
                    .forEachIndexed { index, navigationItem ->
                        if (index == 2) {
                            FloatingActionButton(
//                                    modifier = Modifier.size(40.dp),
                                onClick = {
                                    navController.navigate(Screen.NewRecipeScreen.route)
                                },
                                containerColor = primary100,
                                contentColor = white,
                                shape = CircleShape,
                                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                            ) {
                                Icon(Icons.Filled.Add, "Localized description")
                            }
                        }

                        val currentRoute = navBackStackEntry?.destination?.route
                        selectedItem.intValue = BottomNavigationItem().bottomNavigationItems()
                            .indexOfFirst { it.route == currentRoute }
                        val isSelected = index == selectedItem.intValue
                        NavigationBarItem(
                            selected = currentRoute == navigationItem.route,
                            icon = {
                                Image(
                                    painter = painterResource(id = if (isSelected) navigationItem.icon else navigationItem.disableIcon),
                                    contentDescription = navigationItem.label
                                )
                            },
                            onClick = {
                                navigationSelectedItem = index
                                navController.navigate(navigationItem.route) {
                                    popUpTo(Screen.HomeScreen.route) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = white
                            ),
                            modifier = Modifier.weight(1f)

                        )
                    }
            }
        }

    }) { paddingValues ->
        Surface(
            color = white, modifier = Modifier.fillMaxSize()
        ) {
//        IntroScreen
            NavigationCompose(navController, paddingValues)
        }
    }

}