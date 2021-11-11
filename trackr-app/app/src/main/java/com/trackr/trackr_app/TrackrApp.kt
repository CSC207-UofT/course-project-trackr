package com.trackr.trackr_app

import android.app.Application
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.repository.EventRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TrackrApp : Application() {
    val database by lazy { TrackrDatabase.getDatabase(this)}
    val eventRepository by lazy { EventRepository(database.eventDao()) }
}