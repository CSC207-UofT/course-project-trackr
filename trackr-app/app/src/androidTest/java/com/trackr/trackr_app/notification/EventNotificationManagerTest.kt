package com.trackr.trackr_app.notification

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_NO_CREATE
import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trackr.trackr_app.notification.NotificationConstants.ACTION_RECEIVE_NOTIFICATION
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class EventNotificationManagerTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val enm: EventNotificationManager = EventNotificationManager(context)

    @Test
    fun createNotification() {
        val id = "100"
        enm.createNotification(
                "John", "Birthday",
                LocalDate.of(2022, 10, 10),
                LocalDate.of(2022, 10, 9), id)
        val intent = Intent(context, EventBroadcastReceiver::class.java)
                .setAction(ACTION_RECEIVE_NOTIFICATION)
        val pendingIntent =
                PendingIntent.getBroadcast(
                        context, id.hashCode(), intent, FLAG_IMMUTABLE or FLAG_NO_CREATE)
        assertNotEquals(null, pendingIntent)
    }

    @Test
    fun removeNotification() {
        val id = "200"
        enm.createNotification(
                "Jason", "Birthday",
                LocalDate.of(2022, 10, 10),
                LocalDate.of(2022, 10, 9), id)
        enm.removeNotification(id)
        val intent = Intent(context, EventBroadcastReceiver::class.java)
        val pendingIntent =
                PendingIntent.getBroadcast(
                        context, id.hashCode(), intent, FLAG_IMMUTABLE or FLAG_NO_CREATE)
        assertEquals(null, pendingIntent)
    }
}