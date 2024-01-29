package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.ToolbarComponent
import com.tridya.foodrecipeblog.components.UserProfileSectionUI
import com.tridya.foodrecipeblog.ui.theme.white

@Composable
fun ProfileScreen(navController: NavController, paddingValues: PaddingValues) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = white
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ToolbarComponent(
                toolbarTitle = stringResource(R.string.profile),
                showBackArrow = false,
                showMenuIcon = true,
                onMenuClicked = {})
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                UserProfileSectionUI()
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(
        navController = rememberNavController(),
        paddingValues = PaddingValues(),
    )
}