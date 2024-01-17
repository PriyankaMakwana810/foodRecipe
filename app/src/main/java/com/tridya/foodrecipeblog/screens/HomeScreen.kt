package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.components.ListPopularRecipeByCountry
import com.tridya.foodrecipeblog.components.ListSelectCountry
import com.tridya.foodrecipeblog.components.ProfileSection
import com.tridya.foodrecipeblog.components.SearchBarSection
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.viewModels.HomeViewModel

@Composable
fun HomeScreen(navController: NavController, paddingValues: PaddingValues) {
    val scrollState = rememberScrollState()
    val homeViewModel: HomeViewModel = hiltViewModel()

    val userName = homeViewModel.sharedPreferences.user?.userName
    //Collecting states from ViewModel
//    val searchText by homeViewModel.searchText.collectAsState()
//    val isSearching by homeViewModel.isSearching.collectAsState()
//    val countriesList by homeViewModel.countriesList.collectAsState()

    /*    Scaffold(
            topBar = {
                SearchBar(
                    query = searchText,//text showed on SearchBar
                    onQueryChange = homeViewModel::onSearchTextChange, //update the value of searchText
                    onSearch = homeViewModel::onSearchTextChange, //the callback to be invoked when the input service triggers the ImeAction.Search action
                    active = isSearching, //whether the user is searching or not
                    onActiveChange = { homeViewModel.onToogleSearch() }, //the callback to be invoked when this search bar's active state is changed
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    LazyColumn {
                        items(countriesList) { country ->
                            Text(
                                text = country,
                                modifier = Modifier.padding(
                                    start = 8.dp,
                                    top = 4.dp,
                                    end = 8.dp,
                                    bottom = 4.dp
                                )
                            )
                        }
                    }
                }
            }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it), color = white
            ){}
        }*/

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), color = white
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            ProfileSection(modifier = Modifier.fillMaxWidth(), userName = "Hello $userName")
            SearchBarSection()
            ListSelectCountry()
            ListPopularRecipeByCountry()
        }

    }
}


@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController(), paddingValues = PaddingValues())
}
