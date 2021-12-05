package com.trackr.trackr_app.repository

import com.trackr.trackr_app.model.TrackrEvent
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface EventAccessor {
    fun getAllEvents(): Flow<List<TrackrEvent>>
    fun listFromRange(startDate: LocalDate, endDate: LocalDate): Flow<List<TrackrEvent>>
    suspend fun getEventsInRange(startDate: LocalDate, endDate: LocalDate): List<TrackrEvent>
    suspend fun getById(id: String): TrackrEvent
    fun getByPersonId(personId: String): Flow<List<TrackrEvent>>
}