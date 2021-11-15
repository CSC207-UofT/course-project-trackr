package com.trackr.trackr_app.manager

import com.trackr.trackr_app.model.TrackrEvent
import javax.inject.Inject

class EventManager @Inject constructor() {
    fun createEvent(id: String, type: Int, date: Long, first_year: Int, reminder_interval: Int,
                    reminder_strategy: Int): TrackrEvent {
        return TrackrEvent(id, type, date, first_year, reminder_interval, reminder_strategy)
    }

}