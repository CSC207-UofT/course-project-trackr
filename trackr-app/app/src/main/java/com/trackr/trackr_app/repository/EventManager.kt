package com.trackr.trackr_app.repository

import com.trackr.trackr_app.model.TrackrEvent
import java.sql.Date
import java.util.UUID

class EventManager {
}
fun createEvent(person_id: String, type: Int, date: Long, year: Int, reminder_interval: Int, repeat_strategy: Int): TrackrEvent {
    return TrackrEvent(
        person_id,
        type,
        date,
        year,
        reminder_interval,
        repeat_strategy
    )
}