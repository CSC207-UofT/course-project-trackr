package com.trackr.trackr_app.entities

import com.trackr.trackr_app.entities.eventrepeaterstrategy.EventRepeaterStrategy
import java.time.LocalDate
import kotlin.jvm.JvmOverloads

class Event @JvmOverloads constructor(
    val person: Person,
    val date: LocalDate,
    val eventRepeaterStrategy: EventRepeaterStrategy
) {
    fun getDatesUntil(date: LocalDate): List<LocalDate> =
        eventRepeaterStrategy.getBetween(this.date, date)

    fun getDatesBetween(to: LocalDate, from: LocalDate): List<LocalDate> =
        eventRepeaterStrategy.getBetween(to, from)
}