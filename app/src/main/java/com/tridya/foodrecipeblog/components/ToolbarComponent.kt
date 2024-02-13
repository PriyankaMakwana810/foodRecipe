package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.ui.theme.black

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
            .padding(top = 10.dp,start = 10.dp, end = 10.dp)
    ) {
        if (showBackArrow) {
            Icon(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterStart)
                    .clickable {
                        onBackClicked()
                    },
                painter = painterResource(id = R.drawable.v_ic_arrow_left),
                contentDescription = "arrow"
            )
        }
        SimpleTextComponent(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            value = toolbarTitle,
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            textColor = black
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