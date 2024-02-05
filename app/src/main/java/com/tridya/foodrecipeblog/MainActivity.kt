package com.tridya.foodrecipeblog

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.messaging.FirebaseMessaging
import com.tridya.foodrecipeblog.database.dao.NotificationDao
import com.tridya.foodrecipeblog.ui.theme.FoodRecipeBlogTheme
import com.tridya.foodrecipeblog.utils.Constants
import com.tridya.foodrecipeblog.utils.Constants.NOTIFICATION_ID
import com.tridya.foodrecipeblog.utils.Constants.TAP_ON_NOTIFICATION
import com.tridya.foodrecipeblog.utils.Constants.TOKEN
import com.tridya.foodrecipeblog.utils.PrefUtils
import com.tridya.foodrecipeblog.viewModels.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    @Named(Constants.SHARED_COMMON)
    lateinit var sharedPref: PrefUtils

    @Inject
    lateinit var notificationDao: NotificationDao

    private val splashViewModel: SplashScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Facebook SDK
        FacebookSdk.fullyInitialize()
        AppEventsLogger.activateApp(application)

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { splashViewModel.isLoading.value }

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ), SystemBarStyle.dark(
                Color.BLACK
            )
        )
        isFromNotification(intent)
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed ${task.exception}")
                return@addOnCompleteListener;
            }
            val token = task.result
            Log.e("TAG", "Fetching FCM registration token ${task.result}")

            sharedPref.putString(TOKEN, token)
        }
        setContent {
            FoodRecipeBlogTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    askPermission()
                    FoodRecipeBlogApp()
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            isFromNotification(intent)
        }
    }

    private fun isFromNotification(intent: Intent) {
        val isOpenFromNotification = intent.getBooleanExtra(TAP_ON_NOTIFICATION, false)
        val notificationId = intent.getIntExtra(NOTIFICATION_ID, 0)
        CoroutineScope(Dispatchers.IO).launch {
            notificationDao.updateNotificationRead(isOpenFromNotification, notificationId)
        }
    }

    private fun askPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pushNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private val pushNotificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

}
