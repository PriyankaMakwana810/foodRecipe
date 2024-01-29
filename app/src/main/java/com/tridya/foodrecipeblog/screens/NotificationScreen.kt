package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.CustomNotificationTabs
import com.tridya.foodrecipeblog.components.ListNotificationsByFilter
import com.tridya.foodrecipeblog.components.ListShowNotifications
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun NotificationScreen(navController: NavController, paddingValues: PaddingValues) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ToolbarComponent(
                toolbarTitle = stringResource(R.string.notifications),
                showBackArrow = false
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .scrollable(scrollState, enabled = true, orientation = Orientation.Vertical)

            ) {
                CustomNotificationTabs(onAllClicked = {
                    selectedTab = 0
                }, onReadClicked = {
                    selectedTab = 1
                }, onUnReadClicked = {
                    selectedTab = 2
                })
                when (selectedTab) {
                    0 -> {
                        ListShowNotifications()
                    }

                    1 -> {
                        ListNotificationsByFilter(dateTitle = "Today")
                    }

                    2 -> {
                        ListNotificationsByFilter(dateTitle = "Yesterday")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewNotificationScreen() {
    NotificationScreen(
        navController = rememberNavController(),
        paddingValues = PaddingValues(),
    )
}