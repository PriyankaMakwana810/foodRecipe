package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.database.tables.Notifications
import com.tridya.foodrecipeblog.models.NotificationModel
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.secondary20
import com.tridya.foodrecipeblog.utils.StaticData.dummyNotification
import com.tridya.foodrecipeblog.utils.StaticData.listOfNotifications
import com.tridya.foodrecipeblog.utils.TimeStamp.getRelativeTime
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun NotificationItemComponent(
    modifier: Modifier = Modifier,
    notification: NotificationModel = listOfNotifications.first(),
    isUnread: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(gray4)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Column(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = notification.title,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = black,
                )
            )
            Text(
                text = notification.message,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = gray3,
                ),
            )
            Text(
                text = "10 mins ago",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = gray3,
                ),
            )
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(secondary20)
                .size(30.dp)
        ) {
            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.v_ic_message),
                contentDescription = ""
            )
            if (isUnread) {
                Image(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(2.dp),
                    painter = painterResource(id = R.drawable.v_ic_unread),
                    contentDescription = "unread",
                )
            }
        }
    }
}

@Composable
fun NotificationList(notifications: List<Notifications>) {
    if (notifications.isEmpty()) {
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
                    .size(300.dp),
                placeholder = painterResource(id = R.drawable.nothing_found),
                contentDescription = "Nothing Here!",
                contentScale = ContentScale.Fit,
            )
            Spacer(modifier = Modifier.height(20.dp))
            NormalTextComponent(
                modifier = Modifier,
                value = "Nothing Here",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                align = TextAlign.Center
            )
        }
    } else {
        LazyColumn {
            var currentDate: LocalDate? = null

            items(notifications) { notification ->
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

                })
            }
        }
    }
}

@Composable
fun DateHeader(date: LocalDate) {
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)

    val headerText = when (date) {
        today -> "Today"
        yesterday -> "Yesterday"
        else -> date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
    }
    SimpleTextComponent(
        modifier = Modifier.padding(vertical = 10.dp),
        value = headerText,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        textColor = black
    )
}

@Preview
@Composable
fun ListShowNotifications(
    modifier: Modifier = Modifier,
) {
    Column {
        ListNotificationsByFilter(dateTitle = "Today")
        ListNotificationsByFilter(dateTitle = "Yesterday")
    }
}

@Preview
@Composable
fun ListNotificationsByFilter(
    modifier: Modifier = Modifier,
    dateTitle: String = "Today",
) {
    Column {
        SimpleTextComponent(
            modifier = modifier.padding(vertical = 10.dp),
            value = dateTitle,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textColor = black
        )
        LazyColumn(
            userScrollEnabled = false
        ) {
            items(listOfNotifications) { item: NotificationModel ->
                NotificationItemComponent(notification = item)
            }
        }
    }
}

@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    notification: Notifications = dummyNotification,
    onNotificationClicked: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(gray4)
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .clickable { onNotificationClicked() },
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Column(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = notification.title.toString(),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = black,
                )
            )
            Text(
                text = notification.messageBody.toString(),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = gray3,
                ),
            )
            Text(
                text = getRelativeTime(notification.time!!),
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = gray3,
                ),
            )
        }
        Box(
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(secondary20)
                    .size(30.dp)
            ) {
                Image(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(id = R.drawable.v_ic_message),
                    contentDescription = ""
                )
            }

            if (notification.isRead == false) {
                Image(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(1.dp),
                    painter = painterResource(id = R.drawable.v_ic_unread),
                    contentDescription = "unread",
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
fun NotificationModelList(notifications: List<NotificationModel>) {
    LazyColumn {
        items(notifications) { notification ->
            // Composable for displaying a single notification item
            NotificationItemComponent(notification = notification)
        }
    }
}
