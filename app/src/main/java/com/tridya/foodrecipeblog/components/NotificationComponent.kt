package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.models.NotificationModel
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.secondary20
import com.tridya.foodrecipeblog.utils.StaticData.listOfNotifications

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