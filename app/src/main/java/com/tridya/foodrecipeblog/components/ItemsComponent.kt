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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import com.tridya.foodrecipeblog.models.recipesByCountry
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.blackShadow
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.poppinsFont
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
    padding: Dp = 10.dp,
) {
    Box(
        modifier = modifier
            .height(180.dp)
            .padding(horizontal = 10.dp, vertical = padding)
            .background(
                color = white,
                shape = RoundedCornerShape(10.dp),
            )
            .clickable { onRecipeItemClicked() }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe.strMealThumb)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
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
                        listOf(Color.Transparent, if (isFromDetail) blackShadow else black),
                        0f,
                        500f,
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
                    .height(22.dp)
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
                        text = if (isFromDetail) "" else recipe.strMeal,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = poppinsFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = if (isFromDetail) "" else recipe.postedBy, style = TextStyle(
                            fontSize = 11.sp,
                            fontFamily = poppinsFont,
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
                                fontFamily = poppinsFont,
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

@Composable
fun ReusableDropdownMenu(
    items: List<Triple<String, Painter, () -> Unit>>,
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    offset: DpOffset = DpOffset(x = (-66).dp, y = (-10).dp),
) {
    Box(modifier = Modifier.padding(end = 20.dp)) {
        DropdownMenu(
            modifier = modifier.background(Color.White),
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            offset = offset
        ) {
            items.forEach { (text, iconPainter, onClick) ->
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            painter = iconPainter,
                            contentDescription = text,
                            tint = black,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    },
                    text = { NormalTextComponent(value = text, fontSize = 14.sp) },
                    onClick = {
                        onClick()
                        onDismissRequest()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ListFilterTime(onItemSelected: (String) -> Unit = {}) {
    var selectedItem by remember {
        mutableStateOf(listTimeFilter[0])
    }

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(listTimeFilter) { item ->
            FilterChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = (item == selectedItem),
                onClick = {
                    selectedItem = item
                    onItemSelected(item)
                },
                label = {
                    Text(
                        text = item,
                        style = TextStyle(fontSize = 12.sp),
                        fontFamily = poppinsFont,
                        modifier = Modifier.padding(top = 2.dp)
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
fun ListFilterRate(onItemSelected: (String) -> Unit = {}) {
    var selectedItem by remember {
        mutableStateOf(listRateFilter[1])
    }

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(listRateFilter) { item ->
            InputChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = (item == selectedItem),
                onClick = {
                    selectedItem = item
                    onItemSelected(item)
                },
                label = {
                    Text(
                        text = item,
                        style = TextStyle(fontSize = 12.sp)
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
fun ListFilterCategory(
    listOfCategories: List<String> = listCategoryFilter,
    onItemSelected: (String) -> Unit = {},
) {
    var selectedItem by remember {
        mutableStateOf(listCategoryFilter[0])
    }

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        listOfCategories.forEach { item ->
            FilterChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = (item == selectedItem),
                onClick = {
                    selectedItem = item
                    onItemSelected(item)
                },
                label = {
                    Text(
                        text = item,
                        style = TextStyle(fontSize = 12.sp),
                        fontFamily = poppinsFont,
                        modifier = Modifier.padding(top = 2.dp)
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Preview
@Composable
fun ListFilterIngredients(
    listOfIngredients: List<String> = listCategoryFilter,
    onItemSelected: (String) -> Unit = {},
) {
    var selectedItem by remember {
        mutableStateOf(listOfIngredients[0])
    }

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        listOfIngredients.forEach { item ->
            FilterChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = (item == selectedItem),
                onClick = {
                    selectedItem = item
                    onItemSelected(item)
                },
                label = {
                    Text(
                        text = item, style = TextStyle(fontSize = 12.sp), fontFamily = poppinsFont
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