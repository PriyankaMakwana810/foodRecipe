package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.NormalTextComponent
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.components.SmallTextLabel
import com.tridya.foodrecipeblog.components.TextFieldCustom
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray2
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun NewRecipeScreen(
    navController: NavController,
    paddingValues: PaddingValues,
) {

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ToolbarComponent(showBackArrow = true, toolbarTitle = "Add New Recipe")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
            ) {
                SimpleTextComponent(
                    value = "Add your Favorite Recipe with others",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    textColor = black,
                    textAlign = TextAlign.Start
                )
                NormalTextComponent(
                    value = stringResource(R.string.sharing_a_recipe_is_like_passing_on_a_delicious_secret_a_gift_of_flavors_waiting_to_be_savored_it_s_a_culinary_journey_that_transforms_ingredients_into_memories_inviting_others_to_create_and_share_in_the_joy_of_a_well_loved_dish),
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    textColor = gray2
                )
                Spacer(modifier = Modifier.height(10.dp))
                SmallTextLabel(value = "Recipe Name")
                TextFieldCustom(
                    value = "",
                    hintText = "Enter Recipe Name",
                    onTextChanged = {
                        /*registrationViewModel.onUiEvent(
                            registrationUiEvent = RegistrationUiEvent.NameChanged(
                                inputValue = it
                            )
                        )*/
                    },
//                    isError = registrationState.errorState.nameErrorState.hasError,
//                    errorText = stringResource(id = registrationState.errorState.nameErrorState.errorMessageStringResource)
                )
                SmallTextLabel(value = "Time to cook")
                TextFieldCustom(
                    value = "",
                    hintText = "Enter approx time to cook",
                    onTextChanged = {
                        /*registrationViewModel.onUiEvent(
                            registrationUiEvent = RegistrationUiEvent.NameChanged(
                                inputValue = it
                            )
                        )*/
                    },
//                    isError = registrationState.errorState.nameErrorState.hasError,
//                    errorText = stringResource(id = registrationState.errorState.nameErrorState.errorMessageStringResource)
                )
                SmallTextLabel(value = "Category")
                TextFieldCustom(
                    value = "",
                    hintText = "Enter category of Recipe",
                    onTextChanged = {
                        /*registrationViewModel.onUiEvent(
                            registrationUiEvent = RegistrationUiEvent.NameChanged(
                                inputValue = it
                            )
                        )*/
                    },
//                    isError = registrationState.errorState.nameErrorState.hasError,
//                    errorText = stringResource(id = registrationState.errorState.nameErrorState.errorMessageStringResource)
                )
                SmallTextLabel(value = "Procedure")
                TextFieldCustom(
                    value = "",
                    hintText = "Write down detailed steps about your Recipe",
                    onTextChanged = {
                        /*registrationViewModel.onUiEvent(
                            registrationUiEvent = RegistrationUiEvent.NameChanged(
                                inputValue = it
                            )
                        )*/
                    },
//                    isError = registrationState.errorState.nameErrorState.hasError,
//                    errorText = stringResource(id = registrationState.errorState.nameErrorState.errorMessageStringResource)
                )

            }
        }
    }
}

@Preview
@Composable
fun PreviewAddNewRecipe() {
    NewRecipeScreen(navController = rememberNavController(), paddingValues = PaddingValues())
}