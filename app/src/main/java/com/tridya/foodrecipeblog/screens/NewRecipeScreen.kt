package com.tridya.foodrecipeblog.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.CustomButtonComponent
import com.tridya.foodrecipeblog.components.ImageAddingPreview
import com.tridya.foodrecipeblog.components.LabelText
import com.tridya.foodrecipeblog.components.NormalTextComponent
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray2
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.secondary100
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.showShortToast
import com.tridya.foodrecipeblog.viewModels.NewRecipeViewModel

@Composable
fun NewRecipeScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    newRecipeViewModel: NewRecipeViewModel = hiltViewModel(),
) {

    var photoUri: Uri? by remember { mutableStateOf(null) }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            //When the user has selected a photo, its URL is returned here
            photoUri = uri
        }
    val context = LocalContext.current
//    var showDialog by remember { mutableStateOf(false) }

    val colorsOfTextFields = OutlinedTextFieldDefaults.colors(
        cursorColor = black,
        unfocusedContainerColor = white,
        focusedContainerColor = white,
        unfocusedBorderColor = gray4,
        focusedBorderColor = gray4,
        focusedTextColor = black,
        disabledTextColor = gray2,
        unfocusedTextColor = black
    )
    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ToolbarComponent(
                showBackArrow = true,
                toolbarTitle = stringResource(R.string.add_new_recipe),
                onBackClicked = {
                    navController.navigateUp()
                }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
            ) {
                item {
                    SimpleTextComponent(
                        value = "Share your Favorite Recipe with others",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        textColor = black,
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    NormalTextComponent(
                        value = stringResource(R.string.sharing_a_recipe_is_like_passing_on_a_delicious_secret_a_gift_of_flavors_waiting_to_be_savored_it_s_a_culinary_journey_that_transforms_ingredients_into_memories_inviting_others_to_create_and_share_in_the_joy_of_a_well_loved_dish),
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        textColor = gray2
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    LabelText(title = "Image of Recipe*")
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ImageAddingPreview(photoUri = photoUri, onEditClicked = {
                            launcher.launch(
                                PickVisualMediaRequest(
                                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        })
                    }

                    LabelText(title = "Name of Recipe*")
                    OutlinedTextField(
                        value = newRecipeViewModel.recipeNameState.value,
                        onValueChange = { newRecipeViewModel.recipeNameState.value = it },
                        textStyle = TextStyle(
                            fontSize = 12.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 2.dp, color = gray4, shape = RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        placeholder = {
                            Text(
                                "Name of Recipe",
                                fontSize = 12.sp,
                                color = gray4
                            )
                        },
                        colors = colorsOfTextFields
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    LabelText(title = "Category*")
                    OutlinedTextField(
                        value = newRecipeViewModel.categoryState.value,
                        onValueChange = { newRecipeViewModel.categoryState.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 2.dp, color = gray4, shape = RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        placeholder = {
                            Text(
                                "Category",
                                fontSize = 12.sp,
                                color = gray4
                            )
                        },
                        colors = colorsOfTextFields
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    LabelText(title = "Time to cook*")
                    OutlinedTextField(
                        value = newRecipeViewModel.timeToCookState.value,
                        onValueChange = { newRecipeViewModel.timeToCookState.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 2.dp, color = gray4, shape = RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = {
                            Text(
                                "Time to Cook",
                                fontSize = 12.sp,
                                color = gray4
                            )
                        },
                        colors = colorsOfTextFields
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    LabelText(title = "Procedure *")
                    OutlinedTextField(
                        value = newRecipeViewModel.procedureState.value,
                        onValueChange = { newRecipeViewModel.procedureState.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 2.dp, color = gray4, shape = RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        colors = colorsOfTextFields,
                        placeholder = {
                            Text(
                                "Steps to cook recipe..",
                                fontSize = 12.sp,
                                color = gray4
                            )
                        },
                        minLines = 3
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    newRecipeViewModel.ingredientMeasurementPairs.value.forEachIndexed { index, (ingredient, measurement) ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier.weight(0.5f),
                                verticalArrangement = Arrangement.Top
                            ) {
                                LabelText(title = "Ingredient ${index + 1}*")
                                OutlinedTextField(
                                    value = ingredient,
                                    onValueChange = { updatedIngredient ->
                                        val updatedList =
                                            newRecipeViewModel.ingredientMeasurementPairs.value.toMutableList()
                                        updatedList[index] = updatedIngredient to measurement
                                        newRecipeViewModel.ingredientMeasurementPairs.value =
                                            updatedList
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(
                                            width = 2.dp,
                                            color = gray4,
                                            shape = RoundedCornerShape(10.dp)
                                        ),
                                    shape = RoundedCornerShape(10.dp),
                                    placeholder = {
                                        Text(
                                            "Ingredient ${index + 1}",
                                            fontSize = 12.sp,
                                            color = gray4
                                        )
                                    },
                                    colors = colorsOfTextFields
                                )

                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(
                                modifier = Modifier.weight(0.5f),
                                verticalArrangement = Arrangement.Top
                            ) {
                                LabelText(title = "Measurement ${index + 1}*")
                                OutlinedTextField(
                                    value = measurement,
                                    onValueChange = { updatedMeasurement ->
                                        val updatedList =
                                            newRecipeViewModel.ingredientMeasurementPairs.value.toMutableList()
                                        updatedList[index] = ingredient to updatedMeasurement
                                        newRecipeViewModel.ingredientMeasurementPairs.value =
                                            updatedList
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(
                                            width = 2.dp,
                                            color = gray4,
                                            shape = RoundedCornerShape(10.dp)
                                        ),
                                    shape = RoundedCornerShape(10.dp),
                                    placeholder = {
                                        Text(
                                            "Measurement ${index + 1}",
                                            fontSize = 12.sp,
                                            color = gray4
                                        )
                                    },
                                    colors = colorsOfTextFields
                                )

                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { newRecipeViewModel.addIngredientMeasurementPair() },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        // Leading icon
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "Add",
                            tint = secondary100,
                        )
                        Text(
                            text = "Add Ingredient and Measurement",
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .heightIn(),
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 40.sp,
                                fontWeight = FontWeight.Normal,
                                color = secondary100,
                                textAlign = TextAlign.End
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    LabelText(title = "Area")
                    OutlinedTextField(
                        value = newRecipeViewModel.area.value,
                        onValueChange = { newRecipeViewModel.area.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 2.dp, color = gray4, shape = RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        placeholder = {
                            Text(
                                "Name of Recipe",
                                fontSize = 12.sp,
                                color = gray4
                            )
                        },
                        colors = colorsOfTextFields
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    LabelText(title = "Source")
                    OutlinedTextField(
                        value = newRecipeViewModel.source.value,
                        onValueChange = { newRecipeViewModel.source.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 2.dp, color = gray4, shape = RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        placeholder = {
                            Text(
                                "Name of Recipe",
                                fontSize = 12.sp,
                                color = gray4
                            )
                        },
                        colors = colorsOfTextFields
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    LabelText(title = "YouTube Link")
                    OutlinedTextField(
                        value = newRecipeViewModel.youtubeLink.value,
                        onValueChange = { newRecipeViewModel.youtubeLink.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 2.dp, color = gray4, shape = RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        placeholder = {
                            Text(
                                "Name of Recipe",
                                fontSize = 12.sp,
                                color = gray4
                            )
                        },
                        colors = colorsOfTextFields
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomButtonComponent(
                        value = stringResource(R.string.save_recipe),
                        modifier = Modifier
                            .fillMaxWidth(),
                        onButtonClicked = {
                            if (photoUri != null) {
                                newRecipeViewModel.validateAndSaveRecipe(navController)
                            } else {
                                showShortToast(context, "Please add image of Recipe!!")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewAddNewRecipe() {
    NewRecipeScreen(navController = rememberNavController(), paddingValues = PaddingValues())
}