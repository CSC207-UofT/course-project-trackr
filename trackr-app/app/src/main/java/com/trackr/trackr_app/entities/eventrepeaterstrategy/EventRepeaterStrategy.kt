package com.trackr.trackr_app.entities.eventrepeaterstrategy

import com.trackr.trackr_app.entities.Event;
import java.time.LocalDate

abstract class EventRepeaterStrategy(name: String) {
    init {
        registerRepeater(name)
    }

    abstract fun getBetween(event: Event, from: LocalDate, to: LocalDate): List<LocalDate>;

    fun isBetween(event: Event, from: LocalDate, to: LocalDate): Boolean {
        return getBetween(event, from, to).isNotEmpty()
    }

    companion object {
        private var EVENT_REPEATERS: MutableMap<Int, String> = mutableMapOf()

        fun registerRepeater(name: String) {
            EVENT_REPEATERS.put(name.hashCode(), name)
        }

        fun getStrategyNames(): MutableCollection<String> {
            return EVENT_REPEATERS.values
        }
    }
}