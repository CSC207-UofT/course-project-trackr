package com.trackr.trackr_app.repository

import com.trackr.trackr_app.model.TrackrEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Date
import java.util.UUID

class EventManager(val eventRepository: EventRepository) {
    fun createEvent(person_id: String, type: Int, date: Date, reminder_interval: Int, repeat_strategy: Int): TrackrEvent {
        val trackrEvent = TrackrEvent(
            UUID.randomUUID().toString(),
            person_id,
            type,
            date,
            reminder_interval,
            repeat_strategy
        )
        runBlocking {
            launch {
                eventRepository.insert(trackrEvent)
            }
        }

        return trackrEvent
    }
}