package com.trackr.trackr_app.manager

import java.time.LocalDate

/*
An interface depended on by any view model that needs to add new events to the database.
Any Manager class that creates and adds events to the database should implement this interface.
 */
interface EventCreator {
    suspend fun addEvent(
        personId: String, eventType: Int, chosenReminder: String,
        eventDate: LocalDate
    )
}

