package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.models.IngredientModel
import com.tridya.foodrecipeblog.models.ProcedureModel
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.utils.StaticData

@Preview
@Composable
fun ServeItemCount(modifier: Modifier = Modifier, isProcedure: Boolean = false) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.v_ic_serve),
                contentDescription = ""
            )
            Text(
                text = stringResource(R.string._1_serve),
                modifier = modifier
                    .heightIn(),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight(400),
                    color = gray3,
                    textAlign = TextAlign.Start,
                )
            )
        }
        Text(
            text = if (!isProcedure) {
                "${StaticData.listOfIngredients.size} Items"
            } else {
                "${StaticData.listOfProcedureSteps.size} Steps"
            },
            modifier = modifier
                .heightIn(),
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight(400),
                color = gray3,
                textAlign = TextAlign.Start,
            )
        )
    }
}

@Preview
@Composable
fun IngredientItem(
    modifier: Modifier = Modifier,
    ingredient: IngredientModel = StaticData.listOfIngredients.first(),
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(gray4)
            .padding(vertical = 6.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .clip(shape = RoundedCornerShape(30))
                .background(white),
            model = ImageRequest.Builder(LocalContext.current)
                .data(ingredient.itemImage)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.tomato),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
        )
        Text(
            text = ingredient.itemName,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight(600),
                color = black,
            ),
            modifier = Modifier.weight(1f)
        )
        Text(
            text =
            if (ingredient.itemWeight!! < 1000.0) {
                "${ingredient.itemWeight.toInt()} g"
            } else {
                "${ingredient.itemWeight.toInt() / 1000.0} kg"
            },
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.Normal,
                color = gray3
            )
        )
    }
}

@Preview
@Composable
fun ShowIngredients(modifier: Modifier = Modifier) {
    Column {
        ServeItemCount(modifier)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(StaticData.listOfIngredients) { item ->
                IngredientItem(ingredient = item)
            }
        }
    }
}

@Preview
@Composable
fun ProcedureItem(
    modifier: Modifier = Modifier,
    procedure: ProcedureModel = StaticData.listOfProcedureSteps.first(),
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(gray4)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "Step ${procedure.step}",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                color = black,
            )
        )
        Text(
            text = procedure.stepDetail,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                color = gray3,
            ),
        )
    }
}

@Preview
@Composable
fun ShowProcedure(modifier: Modifier = Modifier) {
    Column {
        ServeItemCount(modifier, true)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(StaticData.listOfProcedureSteps) { item ->
                ProcedureItem(procedure = item)
            }
        }
    }
}
