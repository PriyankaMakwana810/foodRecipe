package com.tridya.foodrecipeblog.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gowtham.ratingbar.RatingBar
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray2
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.poppinsFont
import com.tridya.foodrecipeblog.ui.theme.primary100
import com.tridya.foodrecipeblog.ui.theme.ratingColor
import com.tridya.foodrecipeblog.ui.theme.white

@Preview
@Composable
fun ShareDialogComponent(
    title: String = stringResource(R.string.recipe_share),
    subTitle: String = stringResource(R.string.copy_recipe_link_and_share_your_recipe_link_with_friends_and_family),
    link: String = stringResource(R.string.app_recipe_co_jollof_rice),
    onDismissClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = { onDismissClicked() },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = white
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    NormalTextComponent(
                        value = stringResource(R.string.recipe_link),
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600)
                    )
                    Icon(painter = painterResource(id = R.drawable.baseline_clear_24),
                        contentDescription = "",
                        modifier = Modifier
                            .size(18.dp)
                            .clickable { onDismissClicked() })
                }

                NormalTextComponent(
                    modifier = Modifier.padding(vertical = 10.dp),
                    value = subTitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textColor = gray2,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = gray4, shape = RoundedCornerShape(10.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = link,
                        textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight(500)),
                        onValueChange = {},
                        maxLines = 1,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        shape = RoundedCornerShape(size = 10.dp),
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = gray4,
                            unfocusedBorderColor = gray4,
                        )
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primary100, contentColor = white
                        ),
                        onClick = { onButtonClicked() },
                        interactionSource = NoRippleInteractionSource(),
                        modifier = Modifier.background(
                            color = Color(0xFF129575), shape = RoundedCornerShape(size = 10.dp)
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.copy_link),
                            fontSize = 12.sp,
                            fontWeight = FontWeight(600),
                            color = white,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CommonDialogComponent(
    title: String = stringResource(R.string.app_name),
    subTitle: String = stringResource(R.string.copy_recipe_link_and_share_your_recipe_link_with_friends_and_family),
    onDismissClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = { onDismissClicked() },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = white
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {

                NormalTextComponent(
                    value = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600)
                )
                NormalTextComponent(
                    modifier = Modifier.padding(vertical = 10.dp),
                    value = subTitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textColor = gray2,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primary100, contentColor = white
                        ),
                        onClick = { onButtonClicked() },
                        interactionSource = NoRippleInteractionSource(),
                        modifier = Modifier
                            .background(
                                color = primary100, shape = RoundedCornerShape(size = 10.dp)
                            )
                            .height(40.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.ok),
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            fontFamily = poppinsFont,
                            color = white,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RateDialogComponent(
    title: String = stringResource(R.string.rate_recipe),
    onButtonClicked: () -> Unit = {},
    onDismissClicked: () -> Unit = {},
) {
    var rating: Float by remember { mutableFloatStateOf(0f) }
    var isButtonEnable by remember {
        mutableStateOf(false)
    }
    Dialog(
        onDismissRequest = { onDismissClicked() },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = white
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                NormalTextComponent(
                    modifier = Modifier.padding(vertical = 4.dp),
                    value = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textColor = black,
                    align = TextAlign.Center
                )
                RatingBar(modifier = Modifier
                    .padding(vertical = 12.dp),
                    value = rating,
                    painterEmpty = painterResource(id = R.drawable.v_ic_star_filled),
                    painterFilled = painterResource(id = R.drawable.v_ic_star_review),
                    onValueChange = {
                        rating = it
                    },
                    onRatingChanged = {
                        isButtonEnable = true
                        Log.d("TAG", "onRatingChanged: $it")
                    })
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ratingColor,
                        contentColor = white,
                        disabledContainerColor = gray4,
                        disabledContentColor = white
                    ),
                    enabled = isButtonEnable,
                    onClick = { onButtonClicked() },
                    interactionSource = NoRippleInteractionSource(),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = stringResource(R.string.send),
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = white,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

