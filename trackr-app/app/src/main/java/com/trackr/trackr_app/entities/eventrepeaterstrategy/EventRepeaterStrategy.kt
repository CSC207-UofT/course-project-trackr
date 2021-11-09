package com.trackr.trackr_app.entities.eventrepeaterstrategy

import java.time.LocalDate

abstract class EventRepeaterStrategy(val maximum: LocalDate?) {
    abstract fun getBetween(from: LocalDate, to: LocalDate): List<LocalDate>;
}