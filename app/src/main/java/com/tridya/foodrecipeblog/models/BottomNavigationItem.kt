package com.tridya.foodrecipeblog.models

import androidx.annotation.DrawableRes
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.navigation.Screen

//initializing the data class with default parameters
data class BottomNavigationItem(
    val label: String = "",
    @DrawableRes
    val icon: Int = R.drawable.v_ic_home_filled,
    val route: String = "",
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = R.drawable.v_ic_home_filled,
                route = Screen.HomeScreen.route
            ),
            BottomNavigationItem(
                label = "Saved",
                icon = R.drawable.v_ic_saved_filled,
                route = Screen.SavedScreen.route
            ),
            BottomNavigationItem(
                label = "Notification",
                icon = R.drawable.v_ic_notification_filled,
                route = Screen.NotificationScreen.route
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = R.drawable.v_ic_profile_filled,
                route = Screen.ProfileScreen.route
            ),
        )
    }
}