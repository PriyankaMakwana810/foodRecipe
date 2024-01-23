package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.white

@Preview
@Composable
fun ToolbarComponent(
    showBackArrow: Boolean = true,
    toolbarTitle: String = "",
    onBackClicked: () -> Unit = {},
    onMenuClicked: () -> Unit = {},
    showMenuIcon: Boolean = false,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp, start = 10.dp, end = 10.dp, bottom = 14.dp)
    ) {
        if (showBackArrow) {
            Icon(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        onBackClicked()
                    },
                painter = painterResource(id = R.drawable.v_ic_arrow_left),
                contentDescription = "arrow"
            )
        }
        Text(
            text = toolbarTitle, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            // Text Style/Medium Text/Bold
            style = TextStyle(
                fontSize = 20.sp,
//                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600), color = black, textAlign = TextAlign.Center
            )
        )
        if (showMenuIcon) {
            Icon(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterEnd)
                    .clickable { onMenuClicked() },
                painter = painterResource(id = R.drawable.v_ic_menu),
                contentDescription = "arrow"
            )
        }
    }
}