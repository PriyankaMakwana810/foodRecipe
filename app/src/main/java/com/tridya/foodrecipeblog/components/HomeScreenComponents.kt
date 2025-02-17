package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import com.tridya.foodrecipeblog.models.recipesByCountry
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray1
import com.tridya.foodrecipeblog.ui.theme.gray2
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.poppinsFont
import com.tridya.foodrecipeblog.ui.theme.primary100
import com.tridya.foodrecipeblog.ui.theme.primary80
import com.tridya.foodrecipeblog.ui.theme.secondary20
import com.tridya.foodrecipeblog.ui.theme.secondary40
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.StaticData.listCountries

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    userName: String = "",
    profilePicPath: String = "",
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .background(white),
        ) {
            SimpleTextComponent(
                value = userName,
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                textColor = black,
                textAlign = TextAlign.Left
            )
            SimpleTextComponent(
                modifier = Modifier.padding(),
                value = stringResource(R.string.what_are_you_cooking_today),
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                textColor = gray3,
                textAlign = TextAlign.Left
            )
        }
        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(secondary40),
            model = ImageRequest.Builder(LocalContext.current)
                .data(profilePicPath)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.img_profile),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ListSelectCountry(
    listOfCountries: List<String?> = listCountries,
    onItemSelected: (String) -> Unit = {},
) {
    var selectedItem by remember {
        mutableStateOf(listOfCountries.filterNotNull().firstOrNull())
    }
    LazyRow(
        modifier = Modifier.padding(start = 12.dp, top = 20.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(listOfCountries.filterNotNull()) { item ->
            val isSelected = item == selectedItem
            val textColor = if (isSelected) white else primary80
            FilterChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = (item == selectedItem),
                onClick = {
                    selectedItem = item
                    onItemSelected(item)
                },
                label = {
                    NormalTextComponent(
                        modifier = Modifier.padding(vertical = 7.dp),
                        value = item,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        textColor = textColor,
                        align = TextAlign.Center
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = white,
                    selectedContainerColor = primary100,
                    selectedLabelColor = white,
                    labelColor = primary80
                ),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = white
                ),
                shape = RoundedCornerShape(10.dp)
            )
        }
    }
}

@Preview
@Composable
fun ListPopularRecipeByCountry() {
    LazyRow(
        modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        items(recipesByCountry) { item: RecipeCard ->
            ItemRecipeCard(recipe = item)
        }
    }
}

@Preview
@Composable
fun ListNewRecipe(
) {
    LazyRow(
        modifier = Modifier.padding(start = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        items(recipesByCountry) { item: RecipeCard ->
            ItemNewRecipe(recipe = item)
        }
    }
}

@Composable
fun ItemRecipeCard(
    recipe: RecipeCard,
    onRecipeClicked: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .clickable { onRecipeClicked() }
            .padding(horizontal = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .width(150.dp)
                .zIndex(4f)
                .clip(RectangleShape),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.strMealThumb)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .clip(shape = CircleShape)
                    .size(125.dp),
                placeholder = painterResource(id = R.drawable.food_image),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = secondary20
                ),
                modifier = Modifier
                    .padding(bottom = 1.dp)
                    .height(24.dp)
                    .width(45.dp)
                    .align(Alignment.CenterEnd)
            ) {
                RatingBar(rating = recipe.ratings.toString())
            }
        }
        Box(
            modifier = Modifier
                .width(150.dp)
                .padding(top = 80.dp)
                .height(160.dp)
                .background(color = gray4, shape = RoundedCornerShape(size = 12.dp)),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = recipe.strMeal,
                    modifier = Modifier
                        .width(120.dp),
                    // Text Style/Small Text/Bold
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = gray1,
                        textAlign = TextAlign.Center
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
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
                        text = stringResource(id = R.string.time),
                        // Text Style/Smaller Text/Regular
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight(400),
                            color = gray3,
                        )
                    )
                    Text(
                        text = recipe.timeToCook.toString() + stringResource(id = R.string.mins),
                        // Text Style/Smaller Text/Bold
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight(600),
                            color = gray1,
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .background(
                            color = white,
                            shape = RoundedCornerShape(size = 12.dp)
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

@Composable
fun ItemNewRecipe(recipe: RecipeCard, onRecipeClicked: () -> Unit = {}) {
    Box(modifier = Modifier
        .clickable { onRecipeClicked() }) {
        AsyncImage(
            modifier = Modifier
                .padding(end = 25.dp)
                .clip(shape = CircleShape)
                .size(85.dp)
                .align(alignment = Alignment.TopEnd)
                .zIndex(1f),
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe.strMealThumb)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .error(R.drawable.food_image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.food_image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
        Card(
            colors = CardDefaults.cardColors(
                containerColor = white,
            ),
            elevation = CardDefaults.cardElevation(10.dp),
            modifier = Modifier
                .width(300.dp)
                .padding(vertical = 40.dp, horizontal = 8.dp)
                .background(color = white, shape = RoundedCornerShape(size = 10.dp))
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
            ) {
                Text(
                    modifier = Modifier.width(150.dp),
                    text = recipe.strMeal,
                    // Text Style/Small Text/Bold
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight(600),
                        color = gray1,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                RatingBar(
                    modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                    value = recipe.ratings,
                    style = RatingBarStyle.Fill(),
                    size = 12.dp,
                    spaceBetween = 2.dp,
                    hideInactiveStars = true,
                    onValueChange = {},
                    onRatingChanged = {}
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .size(25.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(recipe.userProfilePhoto)
                                .crossfade(true)
                                .error(R.drawable.img_profile)
                                .memoryCachePolicy(CachePolicy.ENABLED)
                                .diskCachePolicy(CachePolicy.ENABLED)
                                .build(),
                            placeholder = painterResource(id = R.drawable.img_profile),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                        )
                        Text(
                            text = "By ${recipe.postedBy}",
                            // Text Style/Smaller Text/Regular
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight(400),
                                fontFamily = poppinsFont,
                                color = gray3,
                            ),
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.v_ic_timer),
                        contentDescription = stringResource(id = R.string.time)
                    )
                    Text(
                        text = recipe.timeToCook.toString() + stringResource(id = R.string.mins),
                        // Text Style/Smaller Text/Regular
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight(400),
                            fontFamily = poppinsFont,
                            color = gray3,
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ItemRecipeCardPreview() {
    ItemRecipeCard(recipe = recipesByCountry.first())
}

@Preview
@Composable
fun ItemNewRecipeCardPreview() {
    ItemNewRecipe(recipe = recipesByCountry.first())
}

@Preview
@Composable
fun PreviewSearchSection() {
    SearchBarSection()
}

@Preview
@Composable
fun PreviewProfileSection() {
    ProfileSection(userName = "Hello Priyanka")
    SearchBarSection()
}

@Preview
@Composable
fun SearchBarWithFilter(
    modifier: Modifier = Modifier,
    hint: String = "Search Recipe",
    onSearchClicked: () -> Unit = {},
    onTextChange: (String) -> Unit = {},
    onFilterClicked: () -> Unit = {},
    ifHome: Boolean = false,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clickable {
                if (ifHome) {
                    onSearchClicked()
                }
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var text by remember { mutableStateOf(TextFieldValue()) }
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp)),
            value = text,
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontFamily = poppinsFont,
                fontWeight = FontWeight(400),
                color = black
            ),
            onValueChange = {
                text = it
                onTextChange(it.text)
            },
            enabled = !ifHome,
            placeholder = {
                Text(
                    text = hint,
                    color = gray4,
                    fontSize = 12.sp,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal,
                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.v_ic_search),
                    contentDescription = stringResource(R.string.search),
                )
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
            maxLines = 1,
            singleLine = true,
            shape = RoundedCornerShape(size = 10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = black,
                unfocusedContainerColor = white,
                focusedContainerColor = white,
                unfocusedBorderColor = gray4,
                focusedBorderColor = gray4,
                disabledBorderColor = gray4,
                focusedTextColor = black,
                disabledTextColor = gray2,
                unfocusedTextColor = black
            )
        )
        Spacer(modifier = Modifier.width(20.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(color = Color(0xFF129575), shape = RoundedCornerShape(size = 10.dp))
                .clickable { onFilterClicked() }
        ) {
            Image(
                contentDescription = stringResource(R.string.filter),
                painter = painterResource(id = R.drawable.v_ic_filter),
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}

@Composable
fun SearchBarSection(
    modifier: Modifier = Modifier,
    hint: String = stringResource(R.string.search_recipe),
    isEnabled: (Boolean) = true,
    elevation: Dp = 3.dp,
    cornerShape: RoundedCornerShape = RoundedCornerShape(8.dp),
    onSearchClicked: () -> Unit = {},
    onTextChange: (Any) -> Unit = {},
    onFilterClicked: () -> Unit = {},
    ifHome: Boolean = false,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var text by remember { mutableStateOf(TextFieldValue()) }
        Row(
            modifier = Modifier
                .height(50.dp)
                .shadow(elevation = elevation, shape = cornerShape)
                .background(color = white, shape = RoundedCornerShape(8.dp))
                .clickable { onSearchClicked() },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = Color.Transparent, shape = CircleShape)
                    .clickable {
                        if (ifHome) {
                            onSearchClicked()
                        } else {
                            if (text.text.isNotEmpty()) {
                                text = TextFieldValue(text = "")
                                onTextChange("")
                            }
                        }
                    },
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    painter = painterResource(id = R.drawable.v_ic_search),
                    contentDescription = stringResource(R.string.search),
                )
            }
            BasicTextField(
                modifier = modifier
                    .padding(horizontal = 10.dp)
//                .weight(1f)
//                    .width(220.dp)
                    .clickable { if (ifHome) onSearchClicked() },
                value = text,
                onValueChange = {
                    text = it
                    onTextChange(it.text)
                },
                enabled = isEnabled,
                textStyle = TextStyle(
                    color = black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                ),
                decorationBox = { innerTextField ->
                    if (text.text.isEmpty()) {
                        Text(
                            text = hint,
                            color = gray4,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    innerTextField()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
                singleLine = true,
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(color = Color(0xFF129575), shape = RoundedCornerShape(size = 10.dp))
                .clickable { onFilterClicked() }
        ) {
            Image(
                contentDescription = stringResource(R.string.filter),
                painter = painterResource(id = R.drawable.v_ic_filter),
                contentScale = ContentScale.FillBounds,
            )
        }

    }
}


