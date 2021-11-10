package com.trackr.trackr_app.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.trackr.trackr_app.R

/**
 * Class that creates event notifications when receiving a broadcast intent.
 */
class EventBroadcastReceiver : BroadcastReceiver() {
    /**
     * Creates and sends the notifications when receiving a broadcast.
     */
    override fun onReceive(context: Context, intent: Intent) {
        val builder = NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(intent.getStringExtra("contentTitle"))
                .setContentText(intent.getStringExtra("contentText"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(intent.getIntExtra("notificationId", -1), builder.build())
        }
    }
}