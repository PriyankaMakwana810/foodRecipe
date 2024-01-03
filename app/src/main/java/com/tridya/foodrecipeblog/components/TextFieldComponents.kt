package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun TextFieldCustom(hintText: String) {
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


/*@Composable
fun MyTextField(
    lableValue: String,
    painter: Painter,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false,
) {
    var textValue by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        label = {
            Text(text = lableValue)
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        value = textValue,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Black,
            focusedLabelColor = Black,
            cursorColor = Black,
            unfocusedContainerColor = BgColor,
            focusedContainerColor = Color.White
        ),
        isError = !errorStatus,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        onValueChange = {
            textValue = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painter, contentDescription = "")
        }
    )
}*/
