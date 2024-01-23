package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.primary100
import com.tridya.foodrecipeblog.ui.theme.primary80
import com.tridya.foodrecipeblog.ui.theme.secondary20
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.StaticData.listCategoryFilter
import com.tridya.foodrecipeblog.utils.StaticData.listRateFilter
import com.tridya.foodrecipeblog.utils.StaticData.listTimeFilter

@Preview
@Composable
fun RecipesItemsComponent(
    modifier: Modifier = Modifier,
    recipe: RecipeCard = recipesByCountry.first(),
    isFromSaved: Boolean = false,
    isFromDetail: Boolean = false,
    onRecipeItemClicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .height(180.dp)
            .padding(10.dp)
            .background(
                color = white,
                shape = RoundedCornerShape(10.dp),
            )
            .clickable { onRecipeItemClicked() }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(recipe.imageUrl).crossfade(true)
                .build(),
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp)),
            placeholder = painterResource(id = R.drawable.img_recipe_background),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
        )
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
                    containerColor = secondary20
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .height(24.dp)
                    .width(48.dp)
                    .align(Alignment.TopEnd)
            ) {
                RatingBar(rating = recipe.ratings.toString())
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = if (isFromDetail) "" else recipe.name, style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = if (isFromDetail) "" else recipe.postedBy, style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(400),
                            color = gray3,
                        )
                    )
                }
                if (isFromSaved) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.v_ic_timer),
                            contentDescription = "time"
                        )
                        Text(
                            text = "${recipe.timeToCook} Mins",
                            // Text Style/Smaller Text/Regular
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight(400),
                                color = gray3,
                            ), modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                                .background(
                                    color = white, shape = RoundedCornerShape(size = 12.dp)
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                contentDescription = "",
                                contentScale = ContentScale.None,
                                modifier = Modifier
                                    .padding(1.dp)
                                    .size(16.dp)
                                    .align(Alignment.CenterHorizontally),
                                painter = if (recipe.isSaved) {
                                    painterResource(id = R.drawable.v_ic_saved_small)
                                } else {
                                    painterResource(id = R.drawable.v_ic_saved_small_inactive)
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ListFilterTime() {
    var selectedItem by remember {
        mutableStateOf(listTimeFilter[0])
    }

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(listTimeFilter) { item ->
            FilterChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = (item == selectedItem),
                onClick = { selectedItem = item },
                label = {
                    Text(
                        text = item, style = TextStyle(fontSize = 12.sp)
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = white,
                    selectedContainerColor = primary100,
                    selectedLabelColor = white,
                    labelColor = primary80
                ),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = primary80
                ),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ListFilterRate() {
    var selectedItem by remember {
        mutableStateOf(listRateFilter[1])
    }

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(listRateFilter) { item ->
            InputChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = (item == selectedItem),
                onClick = { selectedItem = item },
                label = {
                    Text(
                        text = item,
                        style = TextStyle(fontSize = 12.sp),
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.v_ic_star),
                        contentDescription = "",
                        tint = if (item == selectedItem) white else primary80,
                        modifier = Modifier.size(InputChipDefaults.IconSize)
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = white,
                    selectedContainerColor = primary100,
                    selectedLabelColor = white,
                    labelColor = primary80
                ),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = primary80
                ),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Preview
@Composable
fun ListFilterCategory() {
    var selectedItem by remember {
        mutableStateOf(listCategoryFilter[5])
    }

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        listCategoryFilter.forEach { item ->
            FilterChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = (item == selectedItem),
                onClick = { selectedItem = item },
                label = {
                    Text(
                        text = item, style = TextStyle(fontSize = 12.sp)
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color.White,
                    selectedContainerColor = primary100,
                    selectedLabelColor = Color.White,
                    labelColor = primary80
                ),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = primary80
                ),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}
