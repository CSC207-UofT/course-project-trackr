package com.trackr.trackr_app.viewmodels

import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.TrackrEvent
import java.time.LocalDate

/**
 * A wrapper class used by the UI to display data
 * instead of directly depending on the TrackrEvent entity
 */
class TrackrEventOutput(event: TrackrEvent, person: Person, currentYear: Int) {
    val id = event.id
    val personId = event.personId
    val firstName = person.firstName
    val lastName = person.lastName
    val type = event.type
    val date: LocalDate = LocalDate.ofEpochDay(event.date)
    var eventAge: Int = 0

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