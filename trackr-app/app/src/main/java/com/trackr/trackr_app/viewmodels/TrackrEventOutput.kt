package com.trackr.trackr_app.viewmodels

import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.TrackrEvent
import java.time.LocalDate
import java.util.*

class TrackrEventOutput(event: TrackrEvent, person: Person, currentYear: Int) {
    val id = event.id
    val personId = event.person_id
    val firstName = person.first_name
    val lastName = person.last_name
    val type = event.type
    val date: LocalDate = LocalDate.ofEpochDay(event.date)
    val eventAge: Int = currentYear -  event.firstYear
    val reminderInterval = event.reminder_interval
    val repeatStrategy = event.repeat_strategy
}