package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.models.ReviewModel
import com.tridya.foodrecipeblog.ui.theme.gray1
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.primary40
import com.tridya.foodrecipeblog.ui.theme.primary80
import com.tridya.foodrecipeblog.utils.StaticData.listOfReviews

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ReviewByUserComponent(
    modifier: Modifier = Modifier,
    review: ReviewModel = listOfReviews.first(),
) {
    var likeSelected by remember { mutableStateOf(false) }
    var dislikeSelected by remember { mutableStateOf(false) }

    var likeCount by remember { mutableIntStateOf(review.likedCount) }
    var dislikeCount by remember { mutableIntStateOf(review.disLikedCount) }

    LaunchedEffect(review.likedCount, review.disLikedCount) {
        likeCount = review.likedCount
        dislikeCount = review.disLikedCount
    }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(40.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(review.profilePicPath)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .crossfade(true).build(),
                placeholder = painterResource(id = R.drawable.img_user_profile_1),
                contentDescription = "image description",
                contentScale = ContentScale.Crop,
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                NormalTextComponent(
                    value = review.userName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                NormalTextComponent(value = review.time, fontSize = 10.sp, textColor = gray3)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        NormalTextComponent(value = review.comment, fontSize = 14.sp, textColor = gray1)
        Row(
            modifier = Modifier.padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            FilterChip(
                modifier = Modifier.height(25.dp),
                selected = likeSelected,
                onClick = {
                    if (!likeSelected) {
                        likeSelected = true
                        dislikeSelected = false
                        likeCount++
                        // If dislike was selected before, decrement dislike count
                        if (dislikeCount > 0) dislikeCount--
                    }/* else {
                        likeSelected = false
                        likeCount--
                    }*/
                },
                label = {
                    Text(
                        text = stringResource(R.string.thumbs_up, likeCount),
                        style = TextStyle(fontSize = 12.sp)
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = primary40,
                    labelColor = gray1,
                    selectedContainerColor = primary80,
                    selectedLabelColor = gray1,
                ),
                shape = CircleShape,
                border = null
            )
            FilterChip(
                modifier = Modifier.height(25.dp),
                selected = dislikeSelected,
                onClick = {
                    if (!dislikeSelected) {
                        dislikeSelected = true
                        likeSelected = false
                        dislikeCount++
                        // If like was selected before, decrement like count
                        if (likeCount > 0) likeCount--
                    }/* else {
                        dislikeSelected = false
                        dislikeCount--
                    }*/
                },
                label = {
                    Text(
                        text = stringResource(R.string.thums_down, dislikeCount),
                        style = TextStyle(fontSize = 12.sp)
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = primary40,
                    labelColor = gray1,
                    selectedContainerColor = primary80,
                    selectedLabelColor = gray1,
                ),
                shape = CircleShape,
                border = null

            )
        }
    }
}