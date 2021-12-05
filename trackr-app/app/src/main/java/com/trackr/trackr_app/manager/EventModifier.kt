package com.trackr.trackr_app.manager

import java.time.LocalDate

/*
An interface depended on by any view model that needs to modify existing events in the database.
Any Manager class that can edit or delete events in the database should implement on this interface.
 */
interface EventModifier {
    suspend fun editEvent(eventID: String, reminderInt: Int, eventDate: LocalDate, eventType: Int,
                  personName: String, eventName: String)
    suspend fun deleteEvent(eventID: String)
}