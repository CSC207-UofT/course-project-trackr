package com.trackr.trackr_app.entities.eventrepeaterstrategy

import com.trackr.trackr_app.entities.Event
import com.trackr.trackr_app.entities.Person
import java.time.LocalDate

class EventFactory {
    fun singleEvent(person: Person, date: LocalDate) : Event {
        return Event(person, date, EventNoRepeaterStrategy(date))
    }

    fun annualEvent(person: Person, date: LocalDate) : Event {
        return Event(person, date, EventAnnualRepeaterStrategy(null))
    }
}