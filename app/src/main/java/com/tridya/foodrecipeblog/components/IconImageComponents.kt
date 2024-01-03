package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun SocialIcons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        FloatingActionButton(
            modifier = Modifier.shadow(
                elevation = 5.dp,
                spotColor = Color(0x1A696969),
                ambientColor = Color(0x1A696969)
            ),
            containerColor = white,
            onClick = { },
        ) {
            Icon(painterResource(id = R.drawable.icon_google), "Floating action button.")
        }
        Spacer(modifier = Modifier.width(50.dp))
        FloatingActionButton(
            onClick = { },
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}