package com.trackr.trackr_app.entities.eventrepeaterstrategy

import com.trackr.trackr_app.entities.Event
import java.time.LocalDate

class EventAnnualRepeaterStrategy() : EventRepeaterStrategy("Annual") {
    override fun getBetween(event: Event, from: LocalDate, to: LocalDate): List<LocalDate> {
        var pos = from
        var soFar = mutableListOf<LocalDate>()

        while (!(from.isAfter(to))){
            soFar.add(from)
            pos = pos.plusYears(1)
        }

        return soFar
    }
}