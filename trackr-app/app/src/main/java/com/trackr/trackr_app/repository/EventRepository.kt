package com.trackr.trackr_app.repository

import androidx.annotation.WorkerThread
import com.trackr.trackr_app.database.EventDao
import com.trackr.trackr_app.model.Event
import com.trackr.trackr_app.model.Person
import kotlinx.coroutines.flow.Flow
import java.sql.Date
import java.time.LocalDate

class EventRepository(private val eventDao: EventDao) {
    val allEvents: Flow<List<Event>> = eventDao.listAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun listFromRange(start_date: LocalDate, end_date: LocalDate): Flow<List<Event>> {
        return eventDao.listFromRange(start_date.toEpochDay().toInt(), end_date.toEpochDay().toInt())
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(event: Event) {
        eventDao.insert(event)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(event: Event) {
        eventDao.delete(event.id, event.person_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun editPerson(new_person: Person, event: Event) {
        eventDao.editPerson(new_person.id, event.id, event.person_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun editType(new_type: Int, event: Event) {
        eventDao.editType(new_type, event.id, event.person_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun editDate(new_date: LocalDate, event: Event) {
        eventDao.editDate(new_date.toEpochDay().toInt(), event.id, event.person_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun editInterval(new_interval: Int, event: Event) {
        eventDao.editInterval(new_interval, event.id, event.person_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        eventDao.deleteAll()
    }
}