package com.trackr.trackr_app.repository

import com.trackr.trackr_app.model.TrackrEvent

import androidx.annotation.WorkerThread
import com.trackr.trackr_app.database.EventDao
import com.trackr.trackr_app.model.Person
import kotlinx.coroutines.flow.Flow
import java.sql.Date
import java.time.LocalDate

class EventRepository(private val eventDao: EventDao) {
    val allEvents: Flow<List<TrackrEvent>> = eventDao.listAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun listFromRange(start_date: LocalDate, end_date: LocalDate): Flow<List<TrackrEvent>> {
        return eventDao.listFromRange(Date.valueOf(start_date.toString()), Date.valueOf(end_date.toString()))
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(trackrEvent: TrackrEvent) {
        eventDao.insert(trackrEvent)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(trackrEvent: TrackrEvent) {
        eventDao.delete(trackrEvent.id, trackrEvent.person_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun editPerson(new_person: Person, trackrEvent: TrackrEvent) {
        eventDao.editPerson(new_person.id, trackrEvent.id, trackrEvent.person_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun editType(new_type: Int, trackrEvent: TrackrEvent) {
        eventDao.editType(new_type, trackrEvent.id, trackrEvent.person_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun editDate(new_date: LocalDate, trackrEvent: TrackrEvent) {
        eventDao.editDate(Date.valueOf(new_date.toString()), trackrEvent.id, trackrEvent.person_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun editInterval(new_interval: Int, trackrEvent: TrackrEvent) {
        eventDao.editInterval(new_interval, trackrEvent.id, trackrEvent.person_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        eventDao.deleteAll()
    }
}