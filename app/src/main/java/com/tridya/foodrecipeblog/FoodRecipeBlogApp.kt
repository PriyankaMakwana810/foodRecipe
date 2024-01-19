package com.tridya.foodrecipeblog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.models.BottomNavigationItem
import com.tridya.foodrecipeblog.navigation.NavigationCompose
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.primary100
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.Constants.INTRO
import com.tridya.foodrecipeblog.utils.Constants.LOGIN
import com.tridya.foodrecipeblog.utils.Constants.REGISTER

@Composable
fun FoodRecipeBlogApp() {

    var navigationSelectedItem by remember {
        mutableStateOf(0)
    }
    val navController = rememberNavController()
    var shouldShowBottomBar by remember {
        mutableStateOf(false)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    shouldShowBottomBar = when (navBackStackEntry?.destination?.route) {
        INTRO, LOGIN, REGISTER -> false // on this screen bottom bar should be hidden
        else -> true // in all other cases show bottom bar
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomAppBar(
                    containerColor = white,
                ) {
                    BottomNavigationItem().bottomNavigationItems()
                        .forEachIndexed { index, navigationItem ->
                            NavigationBarItem(

                                selected = index == navigationSelectedItem,
                                icon = {
                                    Icon(
                                        painter = painterResource(id = navigationItem.icon),
                                        contentDescription = navigationItem.label
                                    )
                                },
                                onClick = {
                                    navigationSelectedItem = index
                                    navController.navigate(navigationItem.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = primary100,
                                    disabledIconColor = gray4,
                                    indicatorColor = white
                                )
                            )
                        }
                }
            }

        }
    ) { paddingValues ->
        Surface(
            color = white,
            modifier = Modifier
                .fillMaxSize()
        ) {

//        IntroScreen
            NavigationCompose(navController, paddingValues)
        }
    }

}