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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.CustomNotificationTabs
import com.tridya.foodrecipeblog.components.NotificationList
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.viewModels.NotificationViewModel

@Composable
fun NotificationScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    notificationViewModel: NotificationViewModel = hiltViewModel(),
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()
    val allNotifications by notificationViewModel.allNotifications.collectAsState()
    val readNotifications by notificationViewModel.readNotifications.collectAsState()
    val unreadNotifications by notificationViewModel.unreadNotifications.collectAsState()

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
                        NotificationList(notifications = allNotifications)
                    }

                    1 -> {
                        NotificationList(notifications = readNotifications)
                    }

                    2 -> {
                        NotificationList(notifications = unreadNotifications)
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