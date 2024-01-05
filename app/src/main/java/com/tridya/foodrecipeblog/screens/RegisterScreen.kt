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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.ButtonComponent
import com.tridya.foodrecipeblog.components.CheckboxComponent
import com.tridya.foodrecipeblog.components.ClickableTextLoginComponent
import com.tridya.foodrecipeblog.components.DividerTextComponent
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.components.SmallTextLabel
import com.tridya.foodrecipeblog.components.SocialIcons
import com.tridya.foodrecipeblog.components.TextFieldCustom
import com.tridya.foodrecipeblog.components.TextFieldPassword
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.viewModels.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavController) {
    val registerViewModel: RegisterViewModel = viewModel()
    var textValue by remember {
        mutableStateOf("")
    }
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(white)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            SimpleTextComponent(
                value = "Create an account",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                textColor = black,
                textAlign = TextAlign.Left
            )

            SimpleTextComponent(
                value = "Let’s help you set up your account, it won’t take long.",
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                textColor = black,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.height(10.dp))

            SmallTextLabel(value = "Name")
            TextFieldCustom(hintText = "Enter Name", onTextChanged = {})

            Spacer(modifier = Modifier.height(10.dp))

            SmallTextLabel(value = stringResource(R.string.email))
            TextFieldCustom(
                hintText = stringResource(R.string.enter_email), onTextChanged = {}
            )

            Spacer(modifier = Modifier.height(10.dp))

            SmallTextLabel(value = stringResource(R.string.password))
            TextFieldPassword(hintText = stringResource(R.string.enter_password))

            Spacer(modifier = Modifier.height(10.dp))

            SmallTextLabel(value = "Confirm Password")
            TextFieldPassword(hintText = "Retype Password")

            Spacer(modifier = Modifier.height(20.dp))

            CheckboxComponent(value = "Accept terms & Condition")
//            YellowSmallText(value = stringResource(R.string.forgot_password))

            Spacer(modifier = Modifier.height(20.dp))

            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                value = stringResource(R.string.sign_in),
                onButtonClicked = {
                    navController.navigate(Screen.RegisterScreen.route)
                })
            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()
            Spacer(modifier = Modifier.height(20.dp))
            SocialIcons()
            Spacer(modifier = Modifier.height(50.dp))
            ClickableTextLoginComponent(tryingToLogin = true, onTextSelected = {
                navController.navigate(Screen.LoginScreen.route)
            })
        }

    }
}


@Preview
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(navController = rememberNavController())
}