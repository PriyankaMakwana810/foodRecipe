package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.CustomButtonComponent
import com.tridya.foodrecipeblog.components.ListFilterCategory
import com.tridya.foodrecipeblog.components.ListFilterRate
import com.tridya.foodrecipeblog.components.ListFilterTime
import com.tridya.foodrecipeblog.components.RecipesItemsComponent
import com.tridya.foodrecipeblog.components.SearchBarWithFilter
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.components.TitleSearchResults
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.models.RecipeCard
import com.tridya.foodrecipeblog.models.recipesByCountry
import com.tridya.foodrecipeblog.ui.theme.black
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.viewModels.SearchViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    val allRecipes by searchViewModel.allRecipes.collectAsState()
    val recipesByName by searchViewModel.recipesByName.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        var showBottomSheet by remember {
            mutableStateOf(false)
        }
        LaunchedEffect(Unit) {
//            searchViewModel.getAllRecipes()
        }
        if (showBottomSheet) {
            FilterBottomSheet() {
                showBottomSheet = false
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ToolbarComponent(toolbarTitle = stringResource(R.string.search_recipes),
                onBackClicked = {
                    navController.navigateUp()
                })
            SearchBarWithFilter(
                onSearchClicked = {},
                onFilterClicked = { showBottomSheet = true })
            Column {
                TitleSearchResults(title = "Search Result", results = "${allRecipes.size+1} results")

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    items(allRecipes) { item: RecipeCard ->
                        RecipesItemsComponent(recipe = item)
                    }
                }
            }

        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(onDismiss: () -> Unit = {}) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(containerColor = white, onDismissRequest = { onDismiss() },
        sheetState = SheetState(initialValue = SheetValue.Expanded, skipPartiallyExpanded = true),
        windowInsets = WindowInsets(0, 0, 0, 0),
        dragHandle = { BottomSheetDefaults.DragHandle() }) {
        SimpleTextComponent(
            modifier = Modifier.padding(4.dp),
            value = stringResource(R.string.filter_search),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            textColor = black
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(Modifier.padding(horizontal = 20.dp)) {
            SimpleTextComponent(
                modifier = Modifier.padding(6.dp),
                value = stringResource(R.string.time),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textColor = black,
                textAlign = TextAlign.Start
            )
            ListFilterTime()
            SimpleTextComponent(
                modifier = Modifier.padding(6.dp),
                value = stringResource(R.string.rate),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textColor = black,
                textAlign = TextAlign.Start
            )
            ListFilterRate()
            SimpleTextComponent(
                modifier = Modifier.padding(6.dp),
                value = stringResource(R.string.category),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textColor = black,
                textAlign = TextAlign.Start
            )
            ListFilterCategory()
            CustomButtonComponent(
                modifier = Modifier
                    .width(190.dp)
                    .padding(20.dp)
                    .align(Alignment.CenterHorizontally), value = "Filter"
            ) {
            }
        }

    }
}


@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen(navController = rememberNavController(), paddingValues = PaddingValues())
}
