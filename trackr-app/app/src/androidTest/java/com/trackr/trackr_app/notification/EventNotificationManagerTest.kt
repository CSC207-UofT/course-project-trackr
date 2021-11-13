package com.trackr.trackr_app.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class EventNotificationManagerTest {
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val enm: EventNotificationManager = EventNotificationManager(context)

    @Test
    fun createNotification() {
        val id = 100
        enm.createNotification(
                "John", "Birthday",
                LocalDate.of(2022, 10, 10), id)
        val intent = Intent(context, EventBroadcastReceiver::class.java)
        val pendingIntent =
                PendingIntent.getBroadcast(
                        context, id, intent, PendingIntent.FLAG_NO_CREATE)
        assertNotEquals(null, pendingIntent)
    }

    @Test
    fun removeNotification() {
        val id = 200
        enm.createNotification(
                "Jason", "Birthday",
                LocalDate.of(2022, 10, 10), id)
        enm.removeNotification(id)
        val intent = Intent(context, EventBroadcastReceiver::class.java)
        val pendingIntent =
                PendingIntent.getBroadcast(
                        context, id, intent, PendingIntent.FLAG_NO_CREATE)
        assertEquals(null, pendingIntent)
    }
}