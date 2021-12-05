package com.trackr.trackr_app

import android.app.Application
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.notification.EventNotificationManager
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TrackrApp : Application() {
}