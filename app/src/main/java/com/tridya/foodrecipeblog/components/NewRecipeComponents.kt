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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.ui.theme.Warning1
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray2
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.poppinsFont
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun LabelText(modifier: Modifier = Modifier, title: String = "") {
    Text(
        text = title,
        fontSize = 14.sp,
        fontFamily = poppinsFont,
        modifier = modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun NonOptionalLabelText(modifier: Modifier = Modifier, title: String = "") {
    val annotatedString = buildAnnotatedString {
        // Append the title text
        withStyle(style = SpanStyle(fontSize = 14.sp)) {
            append(title)
        }
        // Append the red asterisk
        withStyle(style = SpanStyle(color = Warning1, fontSize = 14.sp)) {
            append("*")
        }
    }
    Text(
        text = annotatedString,
        modifier = modifier.padding(bottom = 8.dp)
    )
}

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
            fontSize = 12.sp,
            fontFamily = poppinsFont
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
                fontFamily = poppinsFont,
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
    modifier: Modifier = Modifier,
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
            imeAction = ImeAction.Done
        ),
        placeholder = {
            Text(
                placeholder,
                fontSize = 12.sp,
                color = gray4
            )
        },
        colors = colorsOfTextFields,
        singleLine = false,
        minLines = 3,
    )
}
