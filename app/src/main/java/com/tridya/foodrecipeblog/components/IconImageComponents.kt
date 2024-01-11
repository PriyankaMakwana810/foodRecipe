package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.ui.theme.gray3

@Composable
fun SocialIcons(
    modifier: Modifier = Modifier,
    onClickGoogle: () -> Unit = {},
    onClickFacebook: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                onClickGoogle()
            },
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            elevation = ButtonDefaults.buttonElevation(),
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = gray3,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_svg),
                contentDescription = "Google Logo",
            )
        }
        Spacer(modifier = Modifier.width(25.dp))
        Button(
            onClick = {
                onClickFacebook()
            },
            elevation = ButtonDefaults.buttonElevation(),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = gray3,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook_svg),
                contentDescription = "Google Logo",
            )
        }
    }

}