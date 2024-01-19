package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.ui.theme.secondary100

@Composable
fun RatingBar(rating: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(id = R.drawable.v_ic_star),
            tint = secondary100,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(vertical = 6.dp, horizontal = 6.dp)
        )
        Text(
            text = rating,
            style = TextStyle(
                fontSize = 11.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
                textAlign = TextAlign.End
            )
        )
    }
}