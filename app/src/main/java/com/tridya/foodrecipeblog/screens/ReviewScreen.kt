package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.CustomButtonComponent
import com.tridya.foodrecipeblog.components.NormalTextComponent
import com.tridya.foodrecipeblog.components.ReviewByUserComponent
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.models.ReviewModel
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.StaticData.listOfReviews
import com.tridya.foodrecipeblog.utils.TimeStamp.millisToFormat
import com.tridya.foodrecipeblog.utils.showShortToast
import com.tridya.foodrecipeblog.viewModels.CommonViewModel

@Composable
fun ReviewScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    reviewViewModel: CommonViewModel = hiltViewModel(),
) {
    var reviewText by remember { mutableStateOf("") }
    var listOfReviews by remember { mutableStateOf(listOfReviews) }
    val context = LocalContext.current

    val addReview: () -> Unit = {
        if (reviewText.isNotBlank()) {
            val newReview = ReviewModel(
                id = listOfReviews.last().id + 1,
                userName = reviewViewModel.sharedPreferences.user?.userName!!,
                profilePicPath = reviewViewModel.sharedPreferences.user?.profilePicPath!!,
                time = millisToFormat(System.currentTimeMillis().toString()),
                comment = reviewText
            )
            listOfReviews = listOfReviews + newReview
            reviewText = "" // Clear the text field after adding the review
            showShortToast(context, context.getString(R.string.review_added_successfully))
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ToolbarComponent(toolbarTitle = stringResource(R.string.reviews), onBackClicked = {
                navController.navigateUp()
            })
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    NormalTextComponent(
                        value = stringResource(R.string.comments, listOfReviews.size),
                        fontSize = 12.sp,
                        textColor = gray3
                    )
                    NormalTextComponent(value = stringResource(R.string._155_saved), fontSize = 12.sp, textColor = gray3)
                }
                NormalTextComponent(
                    modifier = Modifier.padding(top = 14.dp),
                    value = stringResource(R.string.leave_a_comment),
                    fontSize = 16.sp,
                    textColor = black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = reviewText,
                        onValueChange = {
                            reviewText = it
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.say_something), style = TextStyle(
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight(400),
                                    color = gray4
                                )
                            )
                        },
                        maxLines = 1,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        shape = RoundedCornerShape(size = 15.dp),
                        modifier = Modifier
                            .weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            cursorColor = black,
                            unfocusedContainerColor = white,
                            focusedContainerColor = white,
                            unfocusedBorderColor = gray3,
                            focusedBorderColor = black,
                            focusedTextColor = black
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    CustomButtonComponent(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        value = stringResource(id = R.string.send)
                    ) {
                        addReview()
                    }
                }
                LazyColumn(
                    modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(listOfReviews.reversed()) { item ->
                        ReviewByUserComponent(review = item)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewReviewScreen() {
    ReviewScreen(
        navController = rememberNavController(),
        paddingValues = PaddingValues(),
    )
}