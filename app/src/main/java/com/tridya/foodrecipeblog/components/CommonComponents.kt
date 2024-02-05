package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import com.tridya.foodrecipeblog.models.UserProfile
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray2
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.primary100
import com.tridya.foodrecipeblog.ui.theme.primary80
import com.tridya.foodrecipeblog.ui.theme.secondary100
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.StaticData.notificationTitle
import com.tridya.foodrecipeblog.utils.StaticData.tabsList
import com.tridya.foodrecipeblog.utils.StaticData.userProfile

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
    recipe: RecipeCard = RecipeCard(),
) {
    var isFollowing by remember { mutableStateOf(false) }
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
                .data(recipe.userProfilePhoto)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.img_user_profile_1),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
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
                    text = stringResource(R.string.lagos_nigeria),
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
        CustomButtonComponent(value = stringResource(R.string.follow), isFollowing = isFollowing) {
//            follow clicked
            isFollowing = !isFollowing
        }
    }

}

@Preview
@Composable
fun CustomRecipeDetailsTabs(
    onIngredientClicked: () -> Unit = {},
    onProcedureClicked: () -> Unit = {},
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    TabRow(selectedTabIndex = selectedIndex,
        containerColor = white,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(10)),
        indicator = {
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
                    if (selectedIndex == 0) {
                        onIngredientClicked()
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
fun CustomNotificationTabs(
    onAllClicked: () -> Unit = {},
    onReadClicked: () -> Unit = {},
    onUnReadClicked: () -> Unit = {},
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    TabRow(selectedTabIndex = selectedIndex,
        containerColor = white,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(10)),
        indicator = {
            Box {}
        },
        divider = {}
    ) {
        notificationTitle.forEachIndexed { index, text ->
            val selected = selectedIndex == index
            Tab(
                modifier = if (selected) Modifier
                    .clip(RoundedCornerShape(20))
                    .background(
                        primary100
                    )
                else Modifier.background(white),
                selected = selected,
                onClick = {
                    selectedIndex = index
                    when (selectedIndex) {
                        0 -> {
                            onAllClicked()
                        }

                        1 -> {
                            onReadClicked()
                        }

                        else -> {
                            onUnReadClicked()
                        }
                    }

                },
                text = { Text(text = text, color = if (selected) white else primary80) }
            )
        }
    }
}

@Preview
@Composable
fun TitleSearchResults(
    modifier: Modifier = Modifier,
    title: String = "Recent Search",
    results: String = "",
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NormalTextComponent(
            value = title,
            fontSize = 18.sp,
            fontWeight = FontWeight(600)
        )
        NormalTextComponent(
            value = results,
            fontSize = 14.sp,
            textColor = gray3,
            align = TextAlign.End
        )
    }
}

@Preview
@Composable
fun UserProfileSectionUI(modifier: Modifier = Modifier, user: UserProfile = userProfile) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .clip(shape = CircleShape)
                    .size(130.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userProfile.profilePic)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.img_user_profile_1),
                contentDescription = "image description",
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NormalTextComponent(value = "Recipe", fontSize = 12.sp, textColor = gray4)
                    NormalTextComponent(
                        value = userProfile.numOfRecipes.toString(),
                        fontSize = 20.sp,
                        textColor = black,
                        fontWeight = FontWeight(600)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NormalTextComponent(value = "Followers", fontSize = 12.sp, textColor = gray4)
                    NormalTextComponent(
                        value = userProfile.followers.toString(),
                        fontSize = 20.sp,
                        textColor = black,
                        fontWeight = FontWeight(600)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NormalTextComponent(value = "Following", fontSize = 12.sp, textColor = gray4)
                    NormalTextComponent(
                        value = userProfile.following.toString(),
                        fontSize = 20.sp,
                        textColor = black,
                        fontWeight = FontWeight(600)
                    )
                }
            }
        }
        Column(
            modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            NormalTextComponent(
                value = "Afuwape Abiodun",
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )
            NormalTextComponent(
                modifier = Modifier.padding(top = 5.dp),
                value = "Chef",
                fontSize = 12.sp,
                textColor = gray3
            )
            Spacer(modifier = Modifier.height(15.dp))
            NormalTextComponent(
                value = "Private Chef\n" +
                        "Passionate about food and life \uD83E\uDD58\uD83C\uDF72\uD83C\uDF5D\uD83C\uDF71\n" +
                        "More...",
                fontSize = 12.sp,
                textColor = gray2
            )
        }
    }
}


@Preview
@Composable
fun CustomProfileTabs(
    onRecipeClicked: () -> Unit = {},
    onVideosClicked: () -> Unit = {},
    onTagClicked: () -> Unit = {},
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    TabRow(selectedTabIndex = selectedIndex,
        containerColor = white,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(10)),
        indicator = {
            Box {}
        },
        divider = {}
    ) {
        notificationTitle.forEachIndexed { index, text ->
            val selected = selectedIndex == index
            Tab(
                modifier = if (selected) Modifier
                    .clip(RoundedCornerShape(20))
                    .background(
                        primary100
                    )
                else Modifier.background(white),
                selected = selected,
                onClick = {
                    selectedIndex = index
                    when (selectedIndex) {
                        0 -> {
                            onRecipeClicked()
                        }

                        1 -> {}
                        else -> {}
                    }

                },
                text = { Text(text = text, color = if (selected) white else primary80) }
            )
        }
    }
}

