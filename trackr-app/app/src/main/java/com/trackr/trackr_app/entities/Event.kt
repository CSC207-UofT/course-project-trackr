package com.trackr.trackr_app.entities

import entities.Person
import java.time.LocalDate

abstract class Event constructor(
    val person: Person,
    val date: LocalDate,
    val reminderDeadline: LocalDate? = null
) {
}