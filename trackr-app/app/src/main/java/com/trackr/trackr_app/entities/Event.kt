package com.trackr.trackr_app.entities

import java.time.LocalDate
import kotlin.jvm.JvmOverloads

abstract class Event @JvmOverloads constructor(
    val person: Person,
    val date: LocalDate,
    val reminderDeadline: LocalDate? = null
) {

    /**
     * Check whether the input date matches a reminderDeadline of this object
     * @param date The date to test for a deadline
     * @return A boolean value if the input matches a reminder deadline
     */
    fun isReminderDeadline(date: LocalDate): Boolean {
        return date == reminderDeadline
    }
}