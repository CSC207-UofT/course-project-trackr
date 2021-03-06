package com.trackr.trackr_app.notification

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.trackr.trackr_app.R
import com.trackr.trackr_app.notification.NotificationConstants.ACTION_RECEIVE_NOTIFICATION

/**
 * Class that creates event notifications when receiving a broadcast intent.
 */
class EventBroadcastReceiver : BroadcastReceiver() {
    /**
     * Creates and sends the notifications when receiving a broadcast.
     */
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION_RECEIVE_NOTIFICATION) {
            val id = intent.getStringExtra("notificationId")
            val pendingIntent = id?.let { getNotificationIntent(context, it) }
            val builder = NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(intent.getStringExtra("contentTitle"))
                .setContentText(intent.getStringExtra("contentText"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(context)) {
                notify(id.hashCode(), builder.build())
            }
        }
    }

    /**
     * Returns a pending Intent for a notification.
     * Pending Intent should take user to an Event screen
     *
     * @return pendingIntent to open an Event related activity
     */
    private fun getNotificationIntent(context: Context, id: String): PendingIntent {
        val notifIntent = Intent(
            Intent.ACTION_VIEW,
            "https://events.com/eventId=${id}".toUri(),
        )

        val pendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(notifIntent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        return pendingIntent
    }
}