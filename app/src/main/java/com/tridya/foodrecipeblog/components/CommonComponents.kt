package com.tridya.foodrecipeblog.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import com.tridya.foodrecipeblog.models.User
import com.tridya.foodrecipeblog.models.UserProfile
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.poppinsFont
import com.tridya.foodrecipeblog.ui.theme.primary100
import com.tridya.foodrecipeblog.ui.theme.primary80
import com.tridya.foodrecipeblog.ui.theme.secondary100
import com.tridya.foodrecipeblog.ui.theme.shadow
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.StaticData.notificationTitle
import com.tridya.foodrecipeblog.utils.StaticData.profileTitle
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
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(top = 4.dp),
            text = rating,
            style = TextStyle(
                fontSize = 11.sp,
                fontWeight = FontWeight(400),
                fontFamily = poppinsFont,
                color = black,
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
//            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = recipe.postedBy,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = poppinsFont,
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
                        fontSize = 11.sp,
                        fontFamily = poppinsFont,
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
            .clip(RoundedCornerShape(10.dp)),
        indicator = {
            Box {}
        },
        divider = {}
    ) {
        tabsList.forEachIndexed { index, text ->
            val selected = selectedIndex == index
            Tab(
                modifier = if (selected) Modifier
                    .clip(RoundedCornerShape(10.dp))
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
                text = {
                    NormalTextComponent(
                        value = text,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        textColor = if (selected) white else primary80
                    )
                }
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
                text = {
                    NormalTextComponent(
                        value = text,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        textColor = if (selected) white else primary80
                    )
//                    Text(text = text, color = if (selected) white else primary80)
                }
            )
        }
    }
}

@Preview
@Composable
fun TitleSearchResults(
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.recent_search),
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
fun UserProfileSectionUI(
    modifier: Modifier = Modifier,
    user: UserProfile = userProfile,
    userData: User = User(),
    recipePostedCount: Int = 0,
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .clip(shape = CircleShape)
                    .size(100.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userData.profilePicPath)
                    .allowHardware(true)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .error(R.drawable.img_user_profile_1)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.img_user_profile_1),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(25.dp))

            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileText(
                    stringResource(id = R.string.recipe),
                    recipePostedCount.toString()
                )
                Spacer(modifier = Modifier.width(15.dp))
                ProfileText(
                    stringResource(id = R.string.followers),
                    userProfile.followers.toString()
                )
                Spacer(modifier = Modifier.width(15.dp))
                ProfileText(
                    stringResource(id = R.string.following),
                    userProfile.following.toString()
                )

            }
        }

        Column(
            modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            NormalTextComponent(
                value = userData.userName!!,
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
            ExpandableText(
                text = "Private Chef\n" +
                        "Passionate about food and life \uD83E\uDD58\uD83C\uDF72\uD83C\uDF5D\uD83C\uDF71\n" +
                        "abc\n" +
                        "Private Chef\n" +
                        "Passionate about food and life \uD83E\uDD58\uD83C\uDF72\uD83C\uDF5D\uD83C\uDF71"
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun ProfileText(title: String, body: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NormalTextComponent(value = title, fontSize = 12.sp, textColor = gray4)
        Spacer(modifier = Modifier.height(8.dp))
        NormalTextComponent(
            value = body,
            fontSize = 20.sp,
            textColor = black,
            fontWeight = FontWeight(600)
        )
    }
}

@Preview
@Composable
fun ImageAddingPreview(
    modifier: Modifier = Modifier,
    photoUri: Uri? = null,
    onEditClicked: () -> Unit = {},
) {
    Box {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.Center)
                .clip(shape = CircleShape)
                .size(130.dp)
                .border(width = 3.dp, color = primary80, shape = CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(photoUri ?: R.drawable.food_image)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .error(R.drawable.food_image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.food_image),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
        )
        Icon(
            imageVector = Icons.Default.Edit,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(color = shadow, shape = CircleShape)
                .padding(5.dp)
                .clickable { onEditClicked() },
            contentDescription = "Localized description",
            tint = white,
        )

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
        profileTitle.forEachIndexed { index, text ->
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
                text = {
                    NormalTextComponent(
                        value = text,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        textColor = if (selected) white else primary80
                    )
                }
            )
        }
    }
}

