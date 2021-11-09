package com.trackr.trackr_app.entities.eventrepeaterstrategy

import com.trackr.trackr_app.entities.Event
import java.time.LocalDate

class EventNoRepeaterStrategy() : EventRepeaterStrategy("No") {
    override fun getBetween(event: Event, from: LocalDate, to: LocalDate): List<LocalDate> {
        return if (from.isBefore(event.date) && to.isAfter(event.date)) {
            listOf(event.date)
        } else {
            emptyList()
        }
    }
}