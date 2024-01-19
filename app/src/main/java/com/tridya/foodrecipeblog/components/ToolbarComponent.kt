package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.ui.theme.black

@Preview
@Composable
fun ToolbarComponent(
    toolbarTitle: String = "",
    onBackClicked: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
    ) {
        Icon(
            modifier = Modifier.padding(10.dp),
            painter = painterResource(id = R.drawable.v_ic_arrow_left),
            contentDescription = "arrow"
        )
        Text(
            text = "Search recipes",
            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
            // Text Style/Medium Text/Bold
            style = TextStyle(
                fontSize = 20.sp,
//                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = black,
                textAlign = TextAlign.Center
            )
        )
    }
}