package com.tridya.foodrecipeblog.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.ButtonComponent
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.ui.theme.white
import com.tridya.foodrecipeblog.viewModels.HomeViewModel

@Composable
fun IntroScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.backgroubd_img),
                contentDescription = "bgImage",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "100K+ Premium Recipe",
                    modifier = Modifier
                        .size(100.dp)
                )
                SimpleTextComponent(
                    modifier = Modifier.padding(14.dp),
                    value = stringResource(id = R.string._100k_premium_recipe),
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600)
                )

            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SimpleTextComponent(
                    value = stringResource(id = R.string.get_cooking),
                    fontSize = 50.sp,
                    fontWeight = FontWeight(600)
                )
                Spacer(modifier = Modifier.height(20.dp))

                SimpleTextComponent(
                    value = stringResource(id = R.string.simple_way_to_find_tasty_recipe),
                    fontSize = 16.sp, fontWeight = FontWeight(400)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonComponent(value = "Start Cooking", onButtonClicked = {
                    if (homeViewModel.sharedPreferences.isLoggedIn) {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.IntroScreen.route) {
                                inclusive = false
                            }
                        }
                    }else{
                        navController.navigate(Screen.LoginScreen.route)

                    }
                })
                Spacer(modifier = Modifier.height(100.dp))
            }

        }

    }
}

@Preview
@Composable
fun DefaultPreviewOfIntroScreen() {
    IntroScreen(navController = NavController(LocalContext.current))
}
