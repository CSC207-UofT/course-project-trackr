package com.trackr.trackr_app.notification

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.trackr.trackr_app.R

/**
 * Class that creates event notifications when receiving a broadcast intent.
 */
class EventBroadcastReceiver : BroadcastReceiver() {
    /**
     * Creates and sends the notifications when receiving a broadcast.
     */
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        val id = intent.getStringExtra("notificationId")!!
        val pendingIntent = getNotificationIntent(context, id)
        val builder = NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(intent.getStringExtra("contentTitle"))
                .setContentText(intent.getStringExtra("contentText"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(id.hashCode(), builder.build())
        }
    }

    /**
     * Returns a pending Intent for the a notification.
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