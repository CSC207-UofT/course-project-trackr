package com.trackr.trackr_app

import android.app.Application
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.notification.EventNotificationManager
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TrackrApp : Application() {
    val database by lazy { TrackrDatabase.getDatabase(this)}
    val eventRepository by lazy { EventRepository(database.eventDao()) }
    val userRepository by lazy { UserRepository(database.userDao()) }
    val personRepository by lazy { PersonRepository(database.personDao()) }
    val eventNotificationManager by lazy { EventNotificationManager(this) }
}