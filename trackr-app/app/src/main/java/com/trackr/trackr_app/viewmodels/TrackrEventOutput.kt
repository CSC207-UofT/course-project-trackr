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
    val eventAge: Int = currentYear -  event.firstYear
    val reminderInterval = event.reminderInterval
    val repeatStrategy = event.repeatStrategy
}