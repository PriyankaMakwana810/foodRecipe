package com.tridya.foodrecipeblog.components

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.font.FontFamily
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
import com.tridya.foodrecipeblog.ui.theme.poppinsFont
import com.tridya.foodrecipeblog.ui.theme.secondary100
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun SimpleTextComponent(
    modifier: Modifier = Modifier,
    value: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    fontFamily: FontFamily = poppinsFont,
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
            fontFamily = fontFamily,
            color = textColor,
            textAlign = textAlign,
        )
    )
}

@Composable
fun NormalTextComponent(
    modifier: Modifier = Modifier,
    value: String,
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.Normal,
    textColor: Color = black,
    align: TextAlign = TextAlign.Start,
) {
    Text(
        text = value,
        modifier = modifier,
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
        ),
        color = textColor,
        textAlign = align
    )
}

@Composable
fun SmallTextLabel(modifier: Modifier = Modifier, value: String) {
    Text(
        text = value,
        modifier = modifier
            .heightIn()
            .padding(vertical = 5.dp),
        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Normal,
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
                checkedColor = secondary100,
                uncheckedColor = secondary100
            ),
            interactionSource = NoRippleInteractionSource()
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
            .padding(top = 4.dp)
            .clickable(onClick = { onClick.invoke() }),
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFont,
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
        if (tryingToLogin) stringResource(R.string.already_a_member) else stringResource(R.string.don_t_have_an_account)
    val loginText =
        if (tryingToLogin) stringResource(R.string.sign_in) else stringResource(R.string.sign_up)
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
            fontFamily = poppinsFont,
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
            fontFamily = poppinsFont,
            color = gray4
        )
        Divider(
            modifier = Modifier
                .width(50.dp), color = gray4, thickness = 1.dp
        )
    }
}

const val DEFAULT_MINIMUM_TEXT_LINE = 2

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    fontStyle: FontStyle? = null,
    text: String,
    collapsedMaxLine: Int = DEFAULT_MINIMUM_TEXT_LINE,
    showMoreText: String = "More..",
    showMoreStyle: SpanStyle = SpanStyle(fontWeight = FontWeight.W500),
    showLessText: String = " Show Less",
    showLessStyle: SpanStyle = showMoreStyle,
    textAlign: TextAlign? = null,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var clickable by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier
        .clickable(clickable) {
            isExpanded = !isExpanded
        }
        .then(modifier)
    ) {
        Text(
            modifier = textModifier
                .fillMaxWidth()
                .animateContentSize(),
            text = buildAnnotatedString {
                if (clickable) {
                    if (isExpanded) {
                        append(text)
                        withStyle(style = showLessStyle) { append(showLessText) }
                    } else {
                        val adjustText = text.substring(startIndex = 0, endIndex = lastCharIndex)
                            .dropLast(showMoreText.length)
                            .dropLastWhile { Character.isWhitespace(it) || it == '.' }
                        append("\n" + adjustText)
                        withStyle(style = showMoreStyle) { append(showMoreText) }
                    }
                } else {
                    append(text)
                }
            },
            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
            fontStyle = fontStyle,
            onTextLayout = { textLayoutResult ->
                if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                    clickable = true
                    lastCharIndex = textLayoutResult.getLineEnd(collapsedMaxLine - 1)
                }
            },
            style = style,
            textAlign = textAlign
        )
    }

}