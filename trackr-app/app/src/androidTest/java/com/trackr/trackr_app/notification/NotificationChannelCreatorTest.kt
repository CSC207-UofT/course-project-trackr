package com.trackr.trackr_app.notification

import android.app.NotificationManager
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.trackr.trackr_app.notification.NotificationConstants.CHANNEL_ID
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class NotificationChannelCreatorTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val ncc: NotificationChannelCreator = NotificationChannelCreator(context)
    private val notificationManager : NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @Before
    fun clearChannel() {
        notificationManager.deleteNotificationChannel(CHANNEL_ID)
    }
    @After
    fun deleteChannel() {
        notificationManager.deleteNotificationChannel(CHANNEL_ID)
    }

    @Test
    fun testCreateNotificationChannel() {
        ncc.createNotificationChannel()
        assertNotNull(notificationManager.getNotificationChannel(CHANNEL_ID))
    }
}