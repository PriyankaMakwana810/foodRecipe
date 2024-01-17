package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.models.RecipeCard
import com.tridya.foodrecipeblog.models.recipesByCountry
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray1
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.primary100
import com.tridya.foodrecipeblog.ui.theme.primary80
import com.tridya.foodrecipeblog.ui.theme.secondary100
import com.tridya.foodrecipeblog.ui.theme.secondary40
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    userName: String = "",
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
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                textColor = black,
                textAlign = TextAlign.Left
            )
            SimpleTextComponent(
                modifier = Modifier.padding(vertical = 6.dp),
                value = stringResource(R.string.what_are_you_cooking_today),
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                textColor = gray3,
                textAlign = TextAlign.Left
            )
        }
        Image(
            painter = painterResource(id = R.drawable.img_profile),
            contentDescription = "profile",
            Modifier
                .size(50.dp)
                .clip(shape = RoundedCornerShape(30))
                .background(secondary40),
            contentScale = ContentScale.FillBounds,
        )
    }
}

//@Preview
@Composable
fun SearchBarSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        SearchBar(hint = "Search Recipe")
    }
}

@Composable
fun SearchBar(
    hint: String,
    modifier: Modifier = Modifier,
    isEnabled: (Boolean) = true,
    height: Dp = 50.dp,
    elevation: Dp = 3.dp,
    cornerShape: RoundedCornerShape = RoundedCornerShape(8.dp),
    backgroundColor: Color = Color.White,
    onSearchClicked: () -> Unit = {},
    onTextChange: (Any) -> Unit = {},
) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
            .shadow(elevation = elevation, shape = cornerShape)
            .background(color = backgroundColor, shape = cornerShape)
            .clickable { onSearchClicked() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = modifier
                .weight(1f)
                .size(40.dp)
                .background(color = Color.Transparent, shape = CircleShape)
                .clickable {
                    if (text.text.isNotEmpty()) {
                        text = TextFieldValue(text = "")
                        onTextChange("")
                    }
                },
        ) {
            if (text.text.isNotEmpty()) {
                Icon(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    painter = painterResource(id = R.drawable.baseline_clear_24),
                    contentDescription = stringResource(R.string.search)
                )
            } else {
                Icon(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    painter = painterResource(id = R.drawable.v_ic_search),
                    contentDescription = stringResource(R.string.search),
                )
            }
        }
        BasicTextField(
            modifier = modifier
                .weight(5f)
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            value = text,
            onValueChange = {
                text = it
                onTextChange(it.text)
            },
            enabled = isEnabled,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            decorationBox = { innerTextField ->
                if (text.text.isEmpty()) {
                    Text(
                        text = hint,
                        color = Color.Gray.copy(alpha = 0.5f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
            singleLine = true
        )
    }
}

@Preview
@Composable
fun ListSelectCountry() {
    LazyRow(
        modifier = Modifier.padding(start = 12.dp, top = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(countryData) { item ->
            if (item.isSelected) {
                Text(
                    text = item.countryName,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = white,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .background(color = primary100, shape = RoundedCornerShape(10.dp))
                        .padding(horizontal = 20.dp, vertical = 7.dp)
                )
            } else {
                Text(
                    text = item.countryName,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = primary80,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 7.dp)
                )
            }

        }
    }
}

@Preview
@Composable
fun ListPopularRecipeByCountry() {
    LazyRow(
        modifier = Modifier.padding(start = 15.dp, top = 20.dp, end = 30.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        items(recipesByCountry) { item: RecipeCard ->
            ItemRecipeCard(recipe = item)
        }
    }
}

@Composable
fun ItemRecipeCard(
    recipe: RecipeCard,
) {
    Box {
        Box(
            modifier = Modifier
                .width(150.dp)
                .zIndex(4f)
                .clip(RectangleShape),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.imageUrl)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
                    .size(125.dp),
                placeholder = painterResource(id = R.drawable.food_image),
                contentDescription = "image description",
                contentScale = ContentScale.Crop,
            )
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFE1B3)
                ),
                modifier = Modifier
                    .height(24.dp)
                    .width(50.dp)
                    .align(Alignment.CenterEnd)
            ) {
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
                            .padding(vertical = 6.dp, horizontal = 7.dp)
                    )
                    Text(
                        text = recipe.ratings,
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.End
                        )
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .width(150.dp)
                .padding(top = 80.dp)
                .height(160.dp)
                .background(color =  Color(0xFFD9D9D9), shape = RoundedCornerShape(size = 12.dp)),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = recipe.name,
                    modifier = Modifier
                        .width(120.dp),
                    // Text Style/Small Text/Bold
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = gray1,
                        textAlign = TextAlign.Center
                    )
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
                        text = "Time",
                        // Text Style/Smaller Text/Regular
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight(400),
                            color = gray3,
                        )
                    )
                    Text(
                        text = "${recipe.timeToCook} Mins",
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


@Preview
@Composable
fun ItemRecipeCardPreview() {
    ItemRecipeCard(recipe = recipesByCountry.first())
}

//@Preview
@Composable
fun PreviewSearchSection() {
    SearchBar(hint = "Search Recipe")
}

//@Preview
@Composable
fun PreviewProfileSection() {
    ProfileSection(userName = "Hello Priyanka")
}

private val countryData =
    listOf(
        /*"All", "Indian", "Italian", "Asian", "Chinese", "Spanish", "Franch", "Korean"*/
        CountryWithSelection("All", true),
        CountryWithSelection("Indian", false),
        CountryWithSelection("Italian", false),
        CountryWithSelection("Asian", false),
        CountryWithSelection("Chinese", false),
        CountryWithSelection("Spanish", false),
        CountryWithSelection("French", true),
        CountryWithSelection("Maxican", false),
        CountryWithSelection("Korean", false),
        CountryWithSelection("Thai", false),
        CountryWithSelection("Cousine", false),
        CountryWithSelection("Greek", false),
        CountryWithSelection("All", false),
        CountryWithSelection("All", false),
        CountryWithSelection("All", false),
    )

data class CountryWithSelection(val countryName: String, val isSelected: Boolean)