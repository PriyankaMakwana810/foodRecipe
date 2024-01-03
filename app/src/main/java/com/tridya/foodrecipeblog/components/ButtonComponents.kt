package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.ui.theme.primary100
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun ButtonComponent(modifier: Modifier = Modifier,value: String, onButtonClicked: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = primary100,
            contentColor = white
        ),
        onClick = { onButtonClicked.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(color = Color(0xFF129575), shape = RoundedCornerShape(size = 10.dp))
    ) {
        Text(
            text = value, fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color = white,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSize))
        Icon(painter = painterResource(id = R.drawable.arrow_right), contentDescription = "")

    }

}