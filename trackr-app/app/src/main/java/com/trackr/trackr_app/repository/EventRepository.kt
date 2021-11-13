package com.trackr.trackr_app.repository

import com.trackr.trackr_app.model.TrackrEvent

import androidx.annotation.WorkerThread
import com.trackr.trackr_app.database.EventDao
import com.trackr.trackr_app.model.Person
import kotlinx.coroutines.flow.Flow
import java.sql.Date
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(private val eventDao: EventDao) {
    val allEvents: Flow<List<TrackrEvent>> = eventDao.listAll()

    fun listFromRange(start_date: LocalDate, end_date: LocalDate): Flow<List<TrackrEvent>> {
        return eventDao.listFromRange(start_date.toEpochDay(), end_date.toEpochDay())
    }

    @WorkerThread
    suspend fun getById(id: String): TrackrEvent {
        return eventDao.getById(id)
    }

    @WorkerThread
    suspend fun insert(trackrEvent: TrackrEvent) {
        eventDao.insert(trackrEvent)
    }

    @WorkerThread
    suspend fun delete(trackrEvent: TrackrEvent) {
        eventDao.delete(trackrEvent.id, trackrEvent.person_id)
    }

    @WorkerThread
    suspend fun editPerson(new_person: Person, trackrEvent: TrackrEvent) {
        eventDao.editPerson(new_person.id, trackrEvent.id, trackrEvent.person_id)
    }

    @WorkerThread
    suspend fun editType(new_type: Int, trackrEvent: TrackrEvent) {
        eventDao.editType(new_type, trackrEvent.id, trackrEvent.person_id)
    }

    @WorkerThread
    suspend fun editDate(new_date: LocalDate, event: TrackrEvent) {
        eventDao.editDate(new_date.toEpochDay(), event.id, event.person_id)
    }

    @WorkerThread
    suspend fun editInterval(new_interval: Int, trackrEvent: TrackrEvent) {
        eventDao.editInterval(new_interval, trackrEvent.id, trackrEvent.person_id)
    }

    @WorkerThread
    suspend fun deleteAll() {
        eventDao.deleteAll()
    }
}