package com.trackr.trackr_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.trackr.trackr_app.notification.EventNotificationManager
import com.trackr.trackr_app.ui.main.MainScreen
import com.trackr.trackr_app.ui.theme.TrackrappTheme
import com.trackr.trackr_app.notification.NotificationChannelCreator
import com.trackr.trackr_app.viewmodels.*
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate // FOR TESTING PURPOSES


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create notification channel
        val notifChannelCreator = NotificationChannelCreator(this)
        notifChannelCreator.createNotificationChannel()

        // Create EventNotificationManager FOR TESTING PURPOSES
        val eventNotificationManager = EventNotificationManager(this)

        eventNotificationManager.createNotification(
                "John", "Birthday", LocalDate.now())

        setContent {
            TrackrappTheme {
                MainScreen()
            }
        }


    }
}