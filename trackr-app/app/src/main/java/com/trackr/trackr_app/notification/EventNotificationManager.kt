package com.trackr.trackr_app.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

/**
 * Class to manage notifications for events
 */
class EventNotificationManager(private val context: Context) {
    private val alarmManager: AlarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    private var nextNotificationId: Int = 0
    /**
     * Creates a notification for the given event
     */
    fun createNotification(name: String, eventType: String, remindDate: LocalDate) {
        val instant: Instant = remindDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        val remindDateMillis: Long = instant.toEpochMilli()

        val intent = Intent(context, EventBroadcastReceiver::class.java)
        intent.putExtra("contentTitle", "$name $eventType")
        intent.putExtra("contentText", "You have a $eventType for $name soon.")
        intent.putExtra("notificationId", nextNotificationId)
        nextNotificationId += 1

        val pendingIntent: PendingIntent =
                PendingIntent.getBroadcast(context, 0, intent, FLAG_IMMUTABLE)

        alarmManager.set(AlarmManager.RTC_WAKEUP, remindDateMillis, pendingIntent)
    }
}