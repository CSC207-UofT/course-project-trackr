package com.trackr.trackr_app.entities

import java.time.LocalDate

class Birthday (
    person: Person,
    date: LocalDate,
    reminderDeadline: LocalDate? = null
) : Event(person, date, reminderDeadline)