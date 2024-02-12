package com.tridya.foodrecipeblog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
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
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.gray1
import com.tridya.foodrecipeblog.ui.theme.gray3
import com.tridya.foodrecipeblog.ui.theme.gray4
import com.tridya.foodrecipeblog.ui.theme.poppinsFont
import com.tridya.foodrecipeblog.ui.theme.white

@Preview
@Composable
fun ServeItemCount(
    modifier: Modifier = Modifier,
    isProcedure: Boolean = false,
    itemCount: Int = 0,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.v_ic_serve), contentDescription = ""
            )
            NormalTextComponent(
                value = stringResource(R.string._1_serve),
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                textColor = gray3
            )

        }

        NormalTextComponent(
            value = if (!isProcedure) "$itemCount Items" else "1 Steps",
            fontSize = 12.sp,
            fontWeight = FontWeight(400),
            textColor = gray3
        )

    }
}

@Preview
@Composable
fun IngredientItem(
    modifier: Modifier = Modifier,
    ingredient: String = "tamatos",
    measure: String = "500g",
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(gray4)
            .padding(vertical = 12.dp, horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .clip(shape = RoundedCornerShape(30))
                .background(white),
            model = ImageRequest.Builder(LocalContext.current).data(R.drawable.food_image)
                .memoryCachePolicy(CachePolicy.ENABLED).diskCachePolicy(CachePolicy.ENABLED)
                .crossfade(true).build(),
            placeholder = painterResource(id = R.drawable.tomato),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
        )
        NormalTextComponent(
            modifier = Modifier.weight(1f),
            value = ingredient,
            fontSize = 16.sp,
            fontWeight = FontWeight(600)
        )
        NormalTextComponent(value = measure, fontSize = 14.sp, textColor = gray3)
    }
}


@Composable
fun ShowIngredients(modifier: Modifier = Modifier, recipe: RecipeCard) {
    val ingredientsList: List<String?> = recipe.getIngredientsList().filterNotNull()
    val measuresList: List<String?> = recipe.getMeasuresList().filterNotNull()
    val listOfIngredientMeasure = ingredientsList.zip(measuresList).map { (ingredient, measure) ->
        IngredientMeasure(ingredient, measure)
    }
    Column {
        ServeItemCount(modifier, itemCount = ingredientsList.size)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            items(listOfIngredientMeasure) { item ->
                item.ingredient?.let {
                    item.measure?.let { it1 ->
                        IngredientItem(
                            ingredient = it, measure = it1
                        )
                    }
                }
            }
        }
    }
}

data class IngredientMeasure(val ingredient: String?, val measure: String?)

@Preview
@Composable
fun ProcedureItem(
    modifier: Modifier = Modifier,
    procedure: String = "",
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFD9D9D9))
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        NormalTextComponent(
            value = "Procedure",
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            textColor = black
        )
        Text(
            text = procedure,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFont,
                color = gray1,
//                textAlign = TextAlign.Justify,
                lineHeight = 20.sp,
            ),
        )
    }
}

@Preview
@Composable
fun ShowProcedure(modifier: Modifier = Modifier, procedure: String = "") {
    Column {
        ServeItemCount(modifier, true)
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                ProcedureItem(procedure = procedure)
            }
        }
    }
}
