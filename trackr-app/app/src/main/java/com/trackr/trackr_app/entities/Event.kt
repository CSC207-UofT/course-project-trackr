package com.trackr.trackr_app.entities

import java.time.LocalDate
import java.util.*

data class Event constructor(
    val person: UUID,
    val date: LocalDate,
    val daysBeforeRemind: Int,
    val eventRepeaterStrategy: Int,
    val eventType: Int,
)