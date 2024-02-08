package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.models.ProcedureModel
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray2
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun LabelText(modifier: Modifier = Modifier, title: String = "") {
    Text(
        text = title,
        fontSize = 14.sp,
        modifier = modifier.padding(bottom = 8.dp)
    )
}


/*
@Composable
fun TextInputForField(){
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
}
*/

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
) {
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
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontSize = 12.sp
        ),
        modifier = modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = gray4, shape = RoundedCornerShape(10.dp)),
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(
                placeholder,
                fontSize = 12.sp,
                color = gray4
            )
        },
        colors = colorsOfTextFields,
        singleLine = true,
        maxLines = 1,
    )
}


@Composable
fun ProcedureTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
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
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontSize = 12.sp
        ),
        modifier = modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = gray4, shape = RoundedCornerShape(10.dp)),
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        placeholder = {
            Text(
                placeholder,
                fontSize = 12.sp,
                color = gray4
            )
        },
        colors = colorsOfTextFields,
        minLines = 3,
    )
}
