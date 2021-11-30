package com.trackr.trackr_app.viewmodels

import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.TrackrEvent
import java.time.LocalDate
import java.util.*

/**
 * A wrapper class used by the UI to display data
 * instead of directly depending on the TrackrEvent entity
 */
class TrackrEventOutput(event: TrackrEvent, person: Person, currentYear: Int) {
    val id = event.id
    val personId = event.person_id
    val firstName = person.first_name
    val lastName = person.last_name
    val type = event.type
    val date: LocalDate = LocalDate.ofEpochDay(event.date)
    var eventAge: Int = 0
    val reminderInterval = event.reminder_interval
    val repeatStrategy = event.repeat_strategy

    /**
     * Calculates the event age to initialize it
     */
    init {
        if (this.date.isBefore(LocalDate.now().withYear(2008))) {
            this.eventAge = currentYear -  event.firstYear + 1
        } else {
            this.eventAge = currentYear - event.firstYear
        }
    }
}