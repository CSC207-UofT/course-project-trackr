package com.trackr.trackr_app.repository

import com.trackr.trackr_app.model.TrackrEvent
import java.sql.Date
import java.util.UUID

class EventManager {
}
fun createEvent(person_id: String, type: Int, date: Long, reminder_interval: Int, repeat_strategy: Int): TrackrEvent {
    return TrackrEvent(
        UUID.randomUUID().toString(),
        person_id,
        type,
        date,
        reminder_interval,
        repeat_strategy
    )
}