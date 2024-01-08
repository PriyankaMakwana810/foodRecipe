package com.tridya.foodrecipeblog.screens.register

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
import com.tridya.foodrecipeblog.screens.register.state.RegistrationUiEvent
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.viewModels.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavController) {
    val registrationViewModel: RegisterViewModel = viewModel()

    val registrationState by remember {
        registrationViewModel.registrationState
    }
    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(white)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            SimpleTextComponent(
                value = stringResource(R.string.create_an_account),
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                textColor = black,
                textAlign = TextAlign.Left
            )

            SimpleTextComponent(
                value = stringResource(R.string.let_s_help_you_set_up_your_account_it_won_t_take_long),
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                textColor = black,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.height(10.dp))

            SmallTextLabel(value = stringResource(R.string.name))
            TextFieldCustom(
                value = registrationState.name,
                hintText = stringResource(R.string.enter_name),
                onTextChanged = {
                    registrationViewModel.onUiEvent(
                        registrationUiEvent = RegistrationUiEvent.NameChanged(
                            inputValue = it
                        )
                    )
                },
                isError = registrationState.errorState.nameErrorState.hasError,
                errorText = stringResource(id = registrationState.errorState.nameErrorState.errorMessageStringResource)
            )

            Spacer(modifier = Modifier.height(10.dp))

            SmallTextLabel(value = stringResource(R.string.email))
            TextFieldCustom(
                value = registrationState.emailId,
                hintText = stringResource(R.string.enter_email),
                onTextChanged = {
                    registrationViewModel.onUiEvent(
                        registrationUiEvent = RegistrationUiEvent.EmailChanged(
                            inputValue = it
                        )
                    )
                },
                isError = registrationState.errorState.nameErrorState.hasError,
                errorText = stringResource(id = registrationState.errorState.emailIdErrorState.errorMessageStringResource)
            )

            Spacer(modifier = Modifier.height(10.dp))

            SmallTextLabel(value = stringResource(R.string.password))
            TextFieldPassword(
                value = registrationState.password,
                hintText = stringResource(R.string.enter_password),
                onValueChange = {
                    registrationViewModel.onUiEvent(
                        registrationUiEvent = RegistrationUiEvent.PasswordChanged(
                            inputValue = it
                        )
                    )
                },
                isError = registrationState.errorState.passwordErrorState.hasError,
                errorText = stringResource(id = registrationState.errorState.passwordErrorState.errorMessageStringResource)
            )

            Spacer(modifier = Modifier.height(10.dp))

            SmallTextLabel(value = stringResource(R.string.confirm_password))
            TextFieldPassword(
                value = registrationState.confirmPassword,
                hintText = stringResource(R.string.retype_password),
                onValueChange = {
                    registrationViewModel.onUiEvent(
                        registrationUiEvent = RegistrationUiEvent.ConfirmPasswordChanged(
                            inputValue = it
                        )
                    )
                },
                isError = registrationState.errorState.passwordErrorState.hasError,
                errorText = stringResource(id = registrationState.errorState.confirmPasswordErrorState.errorMessageStringResource)
            )

            Spacer(modifier = Modifier.height(20.dp))

            CheckboxComponent(value = "Accept terms & Condition")
//            YellowSmallText(value = stringResource(R.string.forgot_password))

            Spacer(modifier = Modifier.height(20.dp))

            ButtonComponent(modifier = Modifier.fillMaxWidth(),
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