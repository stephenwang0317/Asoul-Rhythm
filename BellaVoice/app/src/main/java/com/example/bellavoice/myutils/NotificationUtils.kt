package com.example.bellavoice.myutils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_DEFAULT
import androidx.core.app.NotificationManagerCompat
import androidx.media3.common.Player
import androidx.media3.ui.PlayerNotificationManager
import com.example.bellavoice.R
import okhttp3.internal.notify

class NotificationUtils(private val context: Context) {

    private val notificationId = 1
    private val channelId = "music_channel"

    fun showNotification(title: String, isPlaying: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(channelId, "Music Channel")
        }

        val notificationBuilder = buildNotification(title, isPlaying)

        val notificationManager = NotificationManagerCompat.from(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance)
        channel.lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC // 设置通知在锁屏界面也可见
        channel.setSound(null, null) // 设置通知静音，避免被关闭
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun buildNotification(title: String, isPlaying: Boolean): NotificationCompat.Builder {
        // TODO: Customize the notification layout, add play/pause button and other controls.
        val playPauseIcon = if (isPlaying) R.drawable._ava else R.drawable._bella
        return NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable._ava)
            .setContentTitle("My Test Notification")
            .setContentText("This is my test notification in one line...")
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable._bella
                )
            ) // error here
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(
                        "This is my test notification in one line. Made it longer " +
                                "by setting the setStyle property. " +
                                "It should not fit in one line anymore, " +
                                "rather show as a longer notification content."
                    )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOngoing(true)
    }

    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
    private fun PlayerNotiBuilder(player: Player): PlayerNotificationManager {
        val tmp = PlayerNotificationManager.Builder(context, 111, "ASOUL Rhythm")
            .setChannelImportance(IMPORTANCE_DEFAULT)
            .setSmallIconResourceId(R.drawable._ava)
            .setChannelDescriptionResourceId(R.string.app_name)
            .setChannelNameResourceId(R.string.app_name)
            .build()

        tmp.setPlayer(player)
        tmp.setPriority(PRIORITY_DEFAULT)
        tmp.setUseRewindAction(true)
        tmp.setUsePlayPauseActions(true)

        return tmp
    }

    fun showMyNotification(context: Context, player: Player) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(channelId, "Music Channel")
        }
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        val playerNoti = PlayerNotiBuilder(player)
        playerNoti.notify()

    }

}
