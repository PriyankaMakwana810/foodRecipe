package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.ButtonComponent
import com.tridya.foodrecipeblog.components.ClickableTextLoginComponent
import com.tridya.foodrecipeblog.components.DividerTextComponent
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.components.SmallTextLabel
import com.tridya.foodrecipeblog.components.SocialIcons
import com.tridya.foodrecipeblog.components.TextFieldCustom
import com.tridya.foodrecipeblog.components.YellowSmallText
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun LoginScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(white)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            SimpleTextComponent(
                value = stringResource(R.string.hello),
                fontSize = 30.sp,
                fontWeight = FontWeight(600),
                textColor = black,
                textAlign = TextAlign.Left
            )

            SimpleTextComponent(
                value = stringResource(R.string.welcome_back),
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                textColor = black,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(30.dp))
            SmallTextLabel(value = stringResource(R.string.email))
            TextFieldCustom(hintText = stringResource(R.string.enter_email))
            Spacer(modifier = Modifier.height(30.dp))
            SmallTextLabel(value = stringResource(R.string.password))
            TextFieldCustom(hintText = stringResource(R.string.enter_password))
            Spacer(modifier = Modifier.height(20.dp))
            YellowSmallText(value = stringResource(R.string.forgot_password))
            Spacer(modifier = Modifier.height(20.dp))
            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                value = stringResource(R.string.sign_in),
                onButtonClicked = {})
            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()
            Spacer(modifier = Modifier.height(20.dp))
            SocialIcons(modifier = Modifier.padding(20.dp))
            ClickableTextLoginComponent(onTextSelected = {})
        }

    }
}


@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = NavController(LocalContext.current))
}