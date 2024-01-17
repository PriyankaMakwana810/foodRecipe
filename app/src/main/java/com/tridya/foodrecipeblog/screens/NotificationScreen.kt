package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun NotificationScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            SimpleTextComponent(
                modifier = Modifier.align(Alignment.Center),
                value = "Notification Screen",
                fontSize = 30.sp,
                textColor = Color.Black,
                fontWeight = FontWeight(700)
            )

        }
    }
}