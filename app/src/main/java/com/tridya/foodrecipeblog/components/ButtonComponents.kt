package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.ui.theme.poppinsFont
import com.tridya.foodrecipeblog.ui.theme.primary100
import com.tridya.foodrecipeblog.ui.theme.primary20
import com.tridya.foodrecipeblog.ui.theme.white
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun ButtonComponent(modifier: Modifier = Modifier, value: String, onButtonClicked: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = primary100,
            contentColor = white
        ),
        onClick = { onButtonClicked.invoke() },
        interactionSource = NoRippleInteractionSource(),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(color = Color(0xFF129575), shape = RoundedCornerShape(size = 10.dp))
    ) {
        Text(
            text = value, fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color = white,
            fontFamily = poppinsFont,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(painter = painterResource(id = R.drawable.arrow_right), contentDescription = "",modifier = Modifier.padding(top = 4.dp))

    }
}

class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}

@Preview
@Composable
fun CustomButtonComponent(
    modifier: Modifier = Modifier,
    isFollowing: Boolean = false,
    value: String = "",
    onButtonClicked: () -> Unit = {},
) {
    Button(
        colors = if (!isFollowing) {
            ButtonDefaults.buttonColors(
                containerColor = primary100,
                contentColor = white
            )
        } else {
            ButtonDefaults.buttonColors(
                containerColor = primary20,
                contentColor = primary100
            )
        },
        onClick = { onButtonClicked() },
        interactionSource = NoRippleInteractionSource(),
        modifier = modifier
            .heightIn(40.dp)
            .background(
                color = if (!isFollowing) primary100 else primary20,
                shape = RoundedCornerShape(size = 10.dp)
            )
    ) {
        Text(
            text = if (isFollowing) "Following" else value,
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            textAlign = TextAlign.Center,
//            modifier = Modifier.padding(horizontal = 30.dp)
        )
    }
}