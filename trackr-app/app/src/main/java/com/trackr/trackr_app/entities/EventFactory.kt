package com.trackr.trackr_app.entities

import com.trackr.trackr_app.entities.eventrepeaterstrategy.EventAnnualRepeaterStrategy
import com.trackr.trackr_app.entities.eventrepeaterstrategy.EventNoRepeaterStrategy
import java.time.LocalDate

class EventFactory {
    fun singleEvent(person: Person, date: LocalDate) : Event {
        return Event(person, date, EventNoRepeaterStrategy(date))
    }

    fun annualEvent(person: Person, date: LocalDate) : Event {
        return Event(person, date, EventAnnualRepeaterStrategy(null))
    }
}