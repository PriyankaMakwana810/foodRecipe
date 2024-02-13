package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray2
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.poppinsFont
import com.tridya.foodrecipeblog.ui.theme.primary80
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun TextFieldCustom(
    value: String,
    hintText: String,
    onTextChanged: (String) -> Unit,
    isError: Boolean = false,
    errorText: String = "",
) {
    var textValue by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = value,
        onValueChange = {
            textValue = it
            onTextChanged(it)
        },
        textStyle = TextStyle(
            fontSize = 12.sp,
            fontFamily = poppinsFont,
            fontWeight = FontWeight(400),
            color = black
        ),
        placeholder = {
            Text(
                text = hintText, style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight(400),
                    color = gray3
                )
            )
        },
        isError = isError,
        supportingText = {
            if (isError) {
                ErrorTextInputField(text = errorText)
            }
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        shape = RoundedCornerShape(size = 10.dp),
        modifier = Modifier
            .fillMaxWidth(),
//            .border(width = 1.5.dp, color = gray3, shape = RoundedCornerShape(size = 10.dp)),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = black,
            unfocusedContainerColor = white,
            focusedContainerColor = white,
            unfocusedBorderColor = gray3,
            focusedBorderColor = primary80,
            focusedTextColor = black,
            disabledTextColor = gray2,
            unfocusedTextColor = black
        )
    )
}

@Composable
fun TextFieldPassword(
    value: String,
    hintText: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    errorText: String,
    imeAction: ImeAction = ImeAction.Done,
) {
    var textValue by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = value,
        onValueChange = {
            textValue = it
            onValueChange(it)
        },
        textStyle = TextStyle(
            fontSize = 12.sp,
            fontFamily = poppinsFont,
            fontWeight = FontWeight(400),
            color = black
        ),
        placeholder = {
            Text(
                text = hintText, style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight(400),
                    color = gray3
                )
            )
        },
        isError = isError,
        supportingText = {
            if (isError) {
                ErrorTextInputField(text = errorText)
            }
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        visualTransformation = PasswordVisualTransformation(),
        shape = RoundedCornerShape(size = 10.dp),
        modifier = Modifier
            .fillMaxWidth(),
//            .border(width = 1.5.dp, color = gray3, shape = RoundedCornerShape(size = 10.dp)),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = black,
            unfocusedContainerColor = white,
            focusedContainerColor = white,
            unfocusedBorderColor = gray3,
            focusedBorderColor = primary80,
            focusedTextColor = black,
            disabledTextColor = gray2,
            unfocusedTextColor = black
        )
    )
}

@Composable
fun ErrorTextInputField(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = poppinsFont,
            fontWeight = FontWeight(400),
            color = black
        ),
        color = MaterialTheme.colorScheme.error
    )
}