package com.trackr.trackr_app.entities.eventrepeaterstrategy

import java.time.LocalDate

class EventAnnualRepeaterStrategy(maximum: LocalDate?) : EventRepeaterStrategy(maximum) {
    override fun getBetween(from: LocalDate, to: LocalDate): List<LocalDate> {
        var pos = from
        var soFar = mutableListOf<LocalDate>()

        while (!(from.isAfter(to))){
            soFar.add(from)
            pos = pos.plusYears(1)
        }

        return soFar
    }
}