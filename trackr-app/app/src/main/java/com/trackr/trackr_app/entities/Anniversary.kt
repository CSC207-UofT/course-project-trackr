package com.trackr.trackr_app.entities

import com.trackr.trackr_app.entities.Event
import com.trackr.trackr_app.entities.Person
import java.time.LocalDate

class Anniversary(person: Person, date: LocalDate, reminderDeadline: LocalDate?) :
    Event(person, date, reminderDeadline) {
}