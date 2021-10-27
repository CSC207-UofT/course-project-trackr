package com.trackr.trackr_app.entities

import entities.Person

import java.time.LocalDate

class Event(
    val type: EventType,
    val person: Person,  // The person associated with the event
    val date: LocalDate, // The date of the event
    private val customReminders: Set<LocalDate>? = null, // Custom-set reminder times
    private val reminderAlgorithm: ((LocalDate, LocalDate) -> (Boolean))? = null // Algorithm to calculate automatic reminder dates
) {
    enum class EventType() {
        BIRTHDAY,
        ANNIVERSARY,
    }

    fun isReminderDate(date: LocalDate): Boolean {
        if (customReminders != null && customReminders.contains(date)) return true
        if (reminderAlgorithm != null && reminderAlgorithm.invoke(this.date, date)) return true
        if (this.date.equals(date)) return true

        return false
    }

}