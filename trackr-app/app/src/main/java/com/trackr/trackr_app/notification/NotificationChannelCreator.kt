package com.trackr.trackr_app.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

/**
 * Class to create a notification channel and register it with the system
 */
class NotificationChannelCreator(private val context: Context) {

    /**
     * Creates a notification channel and registers it with the system
     */
    fun createNotificationChannel() {
        val name = "Event notifications"
        val descriptionText = "Notifications for events"
        val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(
            NotificationConstants.CHANNEL_ID, name, importance
        ).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}