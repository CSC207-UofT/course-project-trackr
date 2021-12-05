package com.trackr.trackr_app.manager

import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.viewmodels.AddScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import java.time.LocalDate

/*
An interface depended on by any view model that needs to add new events to the database.
Any Manager class that creates and adds events to the database should implement this interface.
 */
interface EventCreator {
    suspend fun addEvent(firstName: String, lastName: String, eventType: Int, chosenReminder: String,
                 eventDate: LocalDate)
}

