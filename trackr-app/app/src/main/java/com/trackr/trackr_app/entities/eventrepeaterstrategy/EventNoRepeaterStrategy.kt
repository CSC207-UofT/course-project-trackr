package com.trackr.trackr_app.entities.eventrepeaterstrategy

import java.time.LocalDate

class EventNoRepeaterStrategy(maximum: LocalDate) : EventRepeaterStrategy(maximum) {
    override fun getBetween(from: LocalDate, to: LocalDate): List<LocalDate> {
        return if (maximum != null && from.isBefore(maximum) && to.isAfter(maximum)) {
            listOf(maximum)
        } else {
            emptyList()
        }
    }
}