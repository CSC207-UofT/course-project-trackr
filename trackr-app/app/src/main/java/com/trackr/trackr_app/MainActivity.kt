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
import java.time.LocalDate // FOR TESTING PURPOSES


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

        // There is definitely a better way to do this
        val homeScreenViewModel: HomeScreenViewModel by viewModels {
            HomeScreenViewModelFactory((application as TrackrApp).eventRepository)
        }
        val addScreenViewModel: AddScreenViewModel by viewModels {
            AddScreenViewModelFactory(
                (application as TrackrApp).eventRepository,
                (application as TrackrApp).personRepository,
                (application as TrackrApp).userRepository)
        }
        val selectScreenViewModel: SelectScreenViewModel by viewModels {
            SelectScreenViewModelFactory((application as TrackrApp).eventRepository)
        }
        val editScreenViewModel: EditScreenViewModel by viewModels {
            EditScreenViewModelFactory(
                (application as TrackrApp).eventRepository,
                (application as TrackrApp).personRepository,
                (application as TrackrApp).userRepository
            )
        }

        setContent {
            TrackrappTheme {
                MainScreen(
                    homeScreenViewModel,
                    addScreenViewModel,
                    selectScreenViewModel,
                    editScreenViewModel
                )
            }
        }


    }
}