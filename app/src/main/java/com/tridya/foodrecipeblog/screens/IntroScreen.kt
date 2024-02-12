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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.components.ButtonComponent
import com.tridya.foodrecipeblog.components.SimpleTextComponent
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.ui.theme.poppinsFont
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
                    .fillMaxHeight()
                    .padding(30.dp),
            ) {
                Spacer(modifier = Modifier.height(80.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_recipe_cap),
                    contentDescription = stringResource(id = R.string._100k_premium_recipe),
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.CenterHorizontally)
                )
                SimpleTextComponent(
                    modifier = Modifier.padding(14.dp),
                    value = stringResource(id = R.string._100k_premium_recipe),
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(id = R.string.get_cooking),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(),
                        style = TextStyle(
                            fontSize = 50.sp,
                            lineHeight = 60.sp,
                            fontWeight = FontWeight(600),
                            fontFamily = poppinsFont,
                            color = white,
                            textAlign = TextAlign.Center,
                        )
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    SimpleTextComponent(
                        value = stringResource(id = R.string.simple_way_to_find_tasty_recipe),
                        fontSize = 16.sp, fontWeight = FontWeight(400)
                    )

                    Spacer(modifier = Modifier.height(70.dp))

                    ButtonComponent(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp),
                        value = stringResource(R.string.start_cooking),
                        onButtonClicked = {
                            if (homeViewModel.sharedPreferences.isLoggedIn) {
                                navController.navigate(Screen.HomeScreen.route) {
                                    popUpTo(Screen.IntroScreen.route) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                navController.navigate(Screen.LoginScreen.route)

                            }
                        })

                    Spacer(modifier = Modifier.height(50.dp))
                }
            }

        }

    }
}

@Preview
@Composable
fun DefaultPreviewOfIntroScreen() {
    IntroScreen(navController = NavController(LocalContext.current))
}
