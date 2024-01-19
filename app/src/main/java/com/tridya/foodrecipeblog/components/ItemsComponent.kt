package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.models.RecipeCard
import com.tridya.foodrecipeblog.models.recipesByCountry
import com.tridya.foodrecipeblog.ui.theme.white

@Preview
@Composable
fun SearchItemsComponent(recipe: RecipeCard = recipesByCountry.first()) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(10.dp)
            .background(
                color = white,
                shape = RoundedCornerShape(10.dp),
            )
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe.imageUrl)
                .crossfade(true)
                .build(),
            modifier = Modifier.fillMaxSize(),
            placeholder = painterResource(id = R.drawable.img_recipe_background),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
        )
        /*Image(
            painter = painterResource(id = R.drawable.img_recipe_background),
            contentDescription = "recipe_background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )*/
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color.Black),
                        0f,  // TODO: set start
                        500f,  // TODO: set end
                    ), shape = RoundedCornerShape(10.dp)
                )
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFE1B3)
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .height(24.dp)
                    .width(48.dp)
                    .align(Alignment.TopEnd)
            ) {
                RatingBar(rating = recipe.ratings.toString())
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(10.dp)
                    .width(130.dp)
            ) {
                Text(
                    text = recipe.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                    )
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = recipe.postedBy,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFA9A9A9),
                    )
                )
            }
        }

    }
}