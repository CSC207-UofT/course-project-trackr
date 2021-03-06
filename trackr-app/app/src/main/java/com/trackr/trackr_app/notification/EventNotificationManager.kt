package com.trackr.trackr_app.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import com.trackr.trackr_app.notification.NotificationConstants.ACTION_RECEIVE_NOTIFICATION
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class to manage notifications for events
 */
@Singleton
class EventNotificationManager @Inject constructor(private val context: Context) {
    private val alarmManager: AlarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager

    /**
     * Creates a notification for the given event
     */
    fun createNotification(
        name: String, eventType: String, eventDate: LocalDate,
        remindDate: LocalDate, id: String
    ) {
        var whichYear = LocalDate.now().year
        if (eventDate.withYear(whichYear).isBefore(LocalDate.now())) {
            whichYear++
        }
        val instant: Instant = remindDate.withYear(whichYear)
            .atStartOfDay(ZoneId.systemDefault()).toInstant()
        val remindDateMillis: Long = instant.toEpochMilli()

        val intent = Intent(context, EventBroadcastReceiver::class.java)
            .setAction(ACTION_RECEIVE_NOTIFICATION)
        intent.putExtra("contentTitle", "$name $eventType")
        intent.putExtra(
            "contentText", "You have a $eventType for $name on" +
                    " ${eventDate.withYear(whichYear)}."
        )
        intent.putExtra("notificationId", id)

        val pendingIntent: PendingIntent =
            PendingIntent.getBroadcast(context, id.hashCode(), intent, FLAG_IMMUTABLE)

        alarmManager.set(AlarmManager.RTC_WAKEUP, remindDateMillis, pendingIntent)
    }

    /**
     * Removes a notification for the given event
     */
    fun removeNotification(id: String) {
        val intent = Intent(context, EventBroadcastReceiver::class.java)

        val pendingIntent: PendingIntent =
            PendingIntent.getBroadcast(context, id.hashCode(), intent, FLAG_IMMUTABLE)

        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
    }

    /**
     * Edits a notification for the given event
     */
    fun editNotification(
        name: String, eventType: String, eventDate: LocalDate,
        remindDate: LocalDate, id: String
    ) {
        removeNotification(id)
        createNotification(name, eventType, eventDate, remindDate, id)
    }
}