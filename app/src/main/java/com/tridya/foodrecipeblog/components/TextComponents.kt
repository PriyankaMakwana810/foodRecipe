package com.tridya.foodrecipeblog.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.secondary100
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun SimpleTextComponent(
    modifier: Modifier = Modifier,
    value: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    textColor: Color = white,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        text = value,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = fontSize,
            lineHeight = 40.sp,
            fontWeight = fontWeight,
            color = textColor,
            textAlign = textAlign,
        )
    )
}

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ), color = black,
        textAlign = TextAlign.Center
    )
}

@Composable
fun SmallTextLabel(modifier: Modifier = Modifier, value: String) {
    Text(
        text = value,
        modifier = modifier
            .heightIn()
            .padding(vertical = 10.dp),
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = black,
    )
}

@Composable
fun CheckboxComponent(value: String) {
    var isChecked by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
            }, colors = CheckboxDefaults.colors(
                checkedColor = secondary100
            )
        )
        YellowSmallText(value, onClick = {})
    }
}

@Composable
fun YellowSmallText(value: String, onClick: () -> Unit) {
    Text(
        text = value,
        Modifier
            .heightIn()
            .clickable(onClick = { onClick.invoke() }),
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = secondary100,
    )
}

@Composable
fun ClickableTextLoginComponent(
    tryingToLogin: Boolean = true,
    onTextSelected: (String) -> Unit,
) {
    val initialText =
        if (tryingToLogin) "Already a member? " else "Don't have an account? "
    val loginText = if (tryingToLogin) "Sign In" else "Sign up"
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = secondary100)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText(modifier =
    Modifier
        .fillMaxWidth()
        .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent: ", "$span")
                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }

        })
}

@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Divider(
            modifier = Modifier
                .width(50.dp), color = gray4, thickness = 1.dp
        )
        Text(
            text = stringResource(R.string.or),
            fontSize = 12.sp,
            modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight(500),
            color = gray4
        )
        Divider(
            modifier = Modifier
                .width(50.dp), color = gray4, thickness = 1.dp
        )
    }
}


