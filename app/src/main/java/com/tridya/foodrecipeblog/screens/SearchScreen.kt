package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.SearchBarSection
import com.tridya.foodrecipeblog.components.SearchItemsComponent
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.models.RecipeCard
import com.tridya.foodrecipeblog.models.recipesByCountry
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun SearchScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        var showBottomSheet by remember {
            mutableStateOf(false)
        }
        if (showBottomSheet) {
            FilterBottomSheet() {
                showBottomSheet = false
            }
        }
        Column(modifier = Modifier.fillMaxSize()) {
            ToolbarComponent(toolbarTitle = stringResource(R.string.search_recipes),
                onBackClicked = {
                    navController.navigateUp()
                })
            SearchBarSection(onSearchClicked = {}, onFilterClicked = { showBottomSheet = true })
            SimpleTextComponent(
                modifier = Modifier.padding(horizontal = 20.dp),
                value = "Recent Search",
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                textColor = black,
                textAlign = TextAlign.Start
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                items(recipesByCountry) { item: RecipeCard ->
                    SearchItemsComponent(item)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }) {

    }
}


@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen(navController = rememberNavController())
}
