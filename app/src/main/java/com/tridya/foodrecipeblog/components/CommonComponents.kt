package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.models.RecipeCard
import com.tridya.foodrecipeblog.models.recipesByCountry
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.primary100
import com.tridya.foodrecipeblog.ui.theme.primary80
import com.tridya.foodrecipeblog.ui.theme.secondary100
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.StaticData.tabsList

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

@Preview
@Composable
fun ProfileSectionOfRecipe(
    modifier: Modifier = Modifier,
    recipe: RecipeCard = recipesByCountry.first(),
) {
    Row(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(50.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://images.unsplash.com/photo-1705582033498-e7384d494759?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.img_user_profile_1),
            contentDescription = "image description",
            contentScale = ContentScale.Fit,
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = recipe.postedBy,
                modifier = modifier
                    .heightIn(),
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight(600),
                    color = black,
                    textAlign = TextAlign.Start,
                )
            )
            Row {
                Image(
                    painter = painterResource(id = R.drawable.v_ic_location),
                    contentDescription = ""
                )
                Text(
                    text = "Lagos, Nigeria",
                    modifier = modifier
                        .heightIn(),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 40.sp,
                        fontWeight = FontWeight(400),
                        color = gray3,
                        textAlign = TextAlign.Start,
                    )
                )
            }
        }
        CustomButtonComponent(value = "Follow") {
//            follow clicked
        }
    }

}

@Preview
@Composable
fun CustomTabs(onIngridentClicked: () -> Unit = {}, onProcedureClicked: () -> Unit = {}) {
    var selectedIndex by remember { mutableStateOf(0) }

    TabRow(selectedTabIndex = selectedIndex,
        containerColor = white,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(10)),
        indicator = { tabPositions: List<TabPosition> ->
            Box {}
        },
        divider = {}
    ) {
        tabsList.forEachIndexed { index, text ->
            val selected = selectedIndex == index
            Tab(
                modifier = if (selected) Modifier
                    .clip(RoundedCornerShape(20))
                    .background(
                        primary100
                    )
                else Modifier
                    .background(
                        white
                    ),
                selected = selected,
                onClick = {
                    selectedIndex = index
                    if (selectedIndex == 1) {
                        onIngridentClicked()
                    } else {
                        onProcedureClicked()
                    }
                },
                text = { Text(text = text, color = if (selected) white else primary80) }
            )
        }
    }
}

@Preview
@Composable
fun ShowIngredients(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.v_ic_serve),
                contentDescription = ""
            )
            Text(
                text = stringResource(R.string._1_serve),
                modifier = modifier
                    .heightIn(),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight(400),
                    color = gray3,
                    textAlign = TextAlign.Start,
                )
            )
        }
    }
}