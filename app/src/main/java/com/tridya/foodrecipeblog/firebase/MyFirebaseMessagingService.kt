package com.tridya.foodrecipeblog.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.tridya.foodrecipeblog.MainActivity
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.database.dao.NotificationDao
import com.tridya.foodrecipeblog.database.tables.Notifications
import com.tridya.foodrecipeblog.utils.Constants.NOTIFICATION_CHANNEL
import com.tridya.foodrecipeblog.utils.Constants.NOTIFICATION_CHANNEL_ID
import com.tridya.foodrecipeblog.utils.Constants.NOTIFICATION_ID
import com.tridya.foodrecipeblog.utils.Constants.SHARED_COMMON
import com.tridya.foodrecipeblog.utils.Constants.TAP_ON_NOTIFICATION
import com.tridya.foodrecipeblog.utils.Constants.TOKEN
import com.tridya.foodrecipeblog.utils.PrefUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MyFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    @Named(SHARED_COMMON)
    lateinit var sharedPref: PrefUtils

    @Inject
    lateinit var notificationDao: NotificationDao

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.e(TAG, "From: ${remoteMessage.from}")

        remoteMessage.notification?.let {
            Log.e(TAG, "Message Notification Body: ${it.body}")
        }
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body
        var notificationId: Int = 0
        val notification = Notifications(
            0,
            title,
            body,
            remoteMessage.sentTime,
            false
        )
        Log.e(TAG, "onMessageReceived: $notification ", )
        CoroutineScope(Dispatchers.Main).launch {
            notificationId = notificationDao.addNotification(notification).toInt()
        }
        sendNotification(
            id = notificationId,
            title = title ?: getString(R.string.app_name),
            messageBody = body ?: ""
        )
    }

    override fun onNewToken(token: String) {
        Log.e(TAG, "Refreshed token: $token")
        sharedPref.putString(TOKEN, token)
    }


    private fun sendNotification(title: String, messageBody: String, id: Int) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(TAP_ON_NOTIFICATION, true)
        intent.putExtra(NOTIFICATION_ID, id)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val requestCode = 0
        val pendingIntent = PendingIntent.getActivity(
            this,
            requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE,
        )
        val channelId = NOTIFICATION_CHANNEL_ID

        // Since android Oreo notification channel is needed.
        val channel = NotificationChannel(
            channelId,
            NOTIFICATION_CHANNEL,
            NotificationManager.IMPORTANCE_DEFAULT,
        )

        val defaultSoundUri =
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.v_ic_app_icon)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
        val notificationId = 0
        notificationManager.notify(notificationId, notificationBuilder.build())

    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }

}