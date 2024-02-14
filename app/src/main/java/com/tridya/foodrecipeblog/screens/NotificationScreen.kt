package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.CustomNotificationTabs
import com.tridya.foodrecipeblog.components.DateHeader
import com.tridya.foodrecipeblog.components.NormalTextComponent
import com.tridya.foodrecipeblog.components.NotificationItem
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.viewModels.NotificationViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

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

    var listOfNotifications by remember { mutableStateOf(allNotifications) }

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
                        listOfNotifications = emptyList()
                        notificationViewModel.getAllNotifications()
                        listOfNotifications = allNotifications
                    }

                    1 -> {
                        listOfNotifications = emptyList()
                        notificationViewModel.getReadNotifications()
                        listOfNotifications = readNotifications
                    }

                    2 -> {
                        listOfNotifications = emptyList()
                        notificationViewModel.getUnreadNotifications()
                        listOfNotifications = unreadNotifications
//                        NotificationList(notifications = unreadNotifications)
                    }
                }
                if (listOfNotifications.isEmpty()) {
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
                                .diskCachePolicy(CachePolicy.ENABLED)
                                .crossfade(true)
                                .build(),
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .size(200.dp),
                            placeholder = painterResource(id = R.drawable.nothing_found),
                            contentDescription = "Nothing Here!",
                            contentScale = ContentScale.Fit,
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        NormalTextComponent(
                            modifier = Modifier,
                            value = "Nothing here!!",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            align = TextAlign.Center
                        )
                    }
                } else {
                    LazyColumn {
                        var currentDate: LocalDate? = null
                        val sortedNotifications = listOfNotifications.sortedByDescending {
                            Instant.ofEpochMilli(it.time ?: 0)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                        }
                        items(sortedNotifications) { notification ->
                            val notificationDate = Instant.ofEpochMilli(notification.time ?: 0)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()

                            // Check if a new date is encountered
                            if (currentDate == null || notificationDate != currentDate) {
                                currentDate = notificationDate
                                // Display date header
                                DateHeader(date = notificationDate)
                            }

                            // Display notification item
                            NotificationItem(notification = notification, onNotificationClicked = {
                                notificationViewModel.updateNotificationState(true, notification.id)
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
fun PreviewNotificationScreen() {
    NotificationScreen(
        navController = rememberNavController(),
        paddingValues = PaddingValues(),
    )
}