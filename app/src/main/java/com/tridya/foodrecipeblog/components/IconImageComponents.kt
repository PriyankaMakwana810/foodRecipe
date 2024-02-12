package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.white

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
                contentDescription = "Facebook Logo",
            )
        }
    }

}

@Preview
@Composable
fun SocialLoginSection(
    modifier: Modifier = Modifier,
    onClickGoogle: () -> Unit = {},
    onClickFacebook: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = white,
            ),
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier
                .size(45.dp)
                .background(color = white, shape = RoundedCornerShape(size = 10.dp))
                .clickable { onClickGoogle() }
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.v_ic_google),
                    contentDescription = "Google Logo",
                )
            }
        }

        Spacer(modifier = Modifier.width(25.dp))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = white,
            ),
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier
                .size(45.dp)
                .background(color = white, shape = RoundedCornerShape(size = 10.dp))
                .clickable { onClickFacebook() }
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.v_ic_facebook),
                    contentDescription = "Facebook Logo",
                )
            }

        }
        /*Button(
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
                contentDescription = "Facebook Logo",
            )
        }*/
    }
}