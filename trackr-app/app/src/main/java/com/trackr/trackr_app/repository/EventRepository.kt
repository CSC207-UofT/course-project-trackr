package com.trackr.trackr_app.repository

import androidx.annotation.WorkerThread
import com.trackr.trackr_app.database.EventDao
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.TrackrEvent
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(private val eventDao: EventDao) : EventAccessor {
    override fun getAllEvents(): Flow<List<TrackrEvent>> {
        return eventDao.listAll()
    }

    override fun listFromRange(startDate: LocalDate, endDate: LocalDate): Flow<List<TrackrEvent>> {
        return eventDao.listFromRange(startDate.toEpochDay(), endDate.toEpochDay())
    }

    @WorkerThread
    override suspend fun getEventsInRange(startDate: LocalDate, endDate: LocalDate): List<TrackrEvent> {
        return eventDao.eventsBetweenDate(startDate.toEpochDay(), endDate.toEpochDay())
    }

    @WorkerThread
    override suspend fun getById(id: String): TrackrEvent {
        return eventDao.getById(id)
    }

    @WorkerThread
    override fun getByPersonId(personId: String): Flow<List<TrackrEvent>> {
        return eventDao.getByPersonId(personId)
    }

    @WorkerThread
    suspend fun insert(trackrEvent: TrackrEvent) {
        eventDao.insert(trackrEvent)
    }

    @WorkerThread
    suspend fun delete(trackrEvent: TrackrEvent) {
        eventDao.delete(trackrEvent.id, trackrEvent.personId)
    }

    @WorkerThread
    suspend fun editPerson(newPerson: Person, trackrEvent: TrackrEvent) {
        eventDao.editPerson(newPerson.id, trackrEvent.id, trackrEvent.personId)
    }

    @WorkerThread
    suspend fun editType(newType: Int, trackrEvent: TrackrEvent) {
        eventDao.editType(newType, trackrEvent.id, trackrEvent.personId)
    }

    @WorkerThread
    suspend fun editDate(newDate: LocalDate, event: TrackrEvent) {
        eventDao.editDate(newDate.toEpochDay(), event.id, event.personId)
    }

    @WorkerThread
    suspend fun editFirstYear(newYear: Int, event: TrackrEvent) {
        eventDao.editFirstYear(newYear, event.id, event.personId)
    }

    @WorkerThread
    suspend fun editInterval(newInterval: Int, trackrEvent: TrackrEvent) {
        eventDao.editInterval(newInterval, trackrEvent.id, trackrEvent.personId)
    }

    @WorkerThread
    suspend fun deleteAll() {
        eventDao.deleteAll()
    }
}