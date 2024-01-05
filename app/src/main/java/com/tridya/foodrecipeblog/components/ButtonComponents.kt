package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.ui.theme.primary100
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.Constants.SIGN_IN_WITH_GOOGLE
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
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSize))
        Icon(painter = painterResource(id = R.drawable.arrow_right), contentDescription = "")

    }
}

class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}

@Composable
fun AuthContent(
    oneTapSignIn: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        SignInButton(
            onClick = oneTapSignIn
        )
    }
}
@Composable
fun SignInButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.padding(bottom = 48.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.secondary60)
        ),
        onClick = onClick
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.google_svg
            ),
            contentDescription = null
        )
        Text(
            text = SIGN_IN_WITH_GOOGLE,
            modifier = Modifier.padding(6.dp),
            fontSize = 18.sp
        )
    }
}