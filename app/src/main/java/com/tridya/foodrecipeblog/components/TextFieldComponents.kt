package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.secondary100
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun TextFieldCustom(hintText: String, onTextChanged: (String) -> Unit) {
    var textValue by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = textValue,

        onValueChange = {
            textValue = it
            onTextChanged(it)
        },
        placeholder = {
            Text(
                text = hintText, style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = gray3
                )
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        shape = RoundedCornerShape(size = 10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.5.dp, color = gray3, shape = RoundedCornerShape(size = 10.dp)),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = black,
            unfocusedContainerColor = white,
            focusedContainerColor = white,
            unfocusedBorderColor = gray3,
            focusedBorderColor = gray3,
        )
    )
}

@Composable
fun TextFieldPassword(hintText: String) {
    var textValue by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = textValue,

        onValueChange = {
            textValue = it
        },
        placeholder = {
            Text(
                text = hintText, style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = gray3
                )
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(size = 10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.5.dp, color = gray3, shape = RoundedCornerShape(size = 10.dp)),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = black,
            unfocusedContainerColor = white,
            focusedContainerColor = white,
            unfocusedBorderColor = gray3,
            focusedBorderColor = gray3,
        ),
    )
}

@Composable
fun AccountQueryComponent(
    textQuery: String,
    textClickable: String,
    navController: NavHostController,
) {
    val annonatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = black, fontSize = 15.sp)) {
            append(textQuery)
        }
        withStyle(style = SpanStyle(color = secondary100, fontSize = 15.sp)) {
            pushStringAnnotation(tag = textClickable, annotation = textClickable)
            append(textClickable)
        }
    }

    ClickableText(text = annonatedString, onClick = {
        annonatedString.getStringAnnotations(it, it)
            .firstOrNull()?.also { annonation ->
                if (annonation.item == "Login") {
                    navController.navigate("Login")
                } else if (annonation.item == "Register") {
                    navController.navigate("Signup")
                }
            }
    })
}
