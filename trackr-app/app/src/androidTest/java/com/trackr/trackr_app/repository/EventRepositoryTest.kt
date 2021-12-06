package com.trackr.trackr_app.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.trackr.trackr_app.database.EventDao
import com.trackr.trackr_app.database.PersonDao
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.database.UserDao
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.model.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class EventRepositoryTest {
    private lateinit var eventRepository: EventRepository
    private lateinit var eventDao: EventDao
    private lateinit var personDao: PersonDao
    private lateinit var userDao: UserDao
    private lateinit var db: TrackrDatabase

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java).allowMainThreadQueries().build()
        userDao = db.userDao()
        personDao = db.personDao()
        eventDao = db.eventDao()
        eventRepository = EventRepository(eventDao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun listFromRange() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent(
                person.id,
                0,
                LocalDate.of(1999, 1, 1).toEpochDay(),
                1999,
                7,
                0
        )

        eventRepository.insert(event1)
        val result1 = eventRepository.listFromRange(
                LocalDate.of(1999, 1, 1),
                LocalDate.of(1999, 1, 1)
        ).first()
        val result2 = eventRepository.listFromRange(
                LocalDate.of(1999, 2, 1),
                LocalDate.of(1999, 2, 1)
        ).first()
        val emptyList: List<TrackrEvent> = emptyList()
        assertEquals(event1.id, result1[0].id)
        assertEquals(emptyList, result2)
    }

    @Test
    fun getEventsInRange() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent(
                person.id,
                0,
                LocalDate.of(2020, 2, 10).toEpochDay(),
                1999,
                7,
                0
        )

        eventRepository.insert(event1)
        val result1 = eventRepository.getEventsInRange(
                LocalDate.of(2020, 2, 10),
                LocalDate.of(2020, 2, 10)
        )
        val result2 = eventRepository.getEventsInRange(
                LocalDate.of(2020, 2, 1),
                LocalDate.of(2020, 2, 1)
        )
        val emptyList: List<TrackrEvent> = emptyList()
        assertEquals(event1.id, result1[0].id)
        assertEquals(emptyList, result2)
    }

    @Test
    fun getById() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent(
                person.id,
                0,
                LocalDate.of(2008, 12, 15).toEpochDay(),
                1984,
                7,
                0
        )

        eventRepository.insert(event1)
        val result = eventRepository.getById(event1.id)
        assertEquals(event1.id, result.id)
    }

    @Test
    fun insert() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent(
                person.id,
                0,
                LocalDate.of(2008, 12, 15).toEpochDay(),
                1984,
                7,
                0
        )

        eventRepository.insert(event1)
        val result = eventRepository.getAllEvents().first()
        assertEquals(event1.id, result[0].id)
    }

    @Test
    fun delete() = runBlocking{
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent(
                person.id,
                0,
                LocalDate.of(2008, 12, 15).toEpochDay(),
                1984,
                7,
                0
        )

        eventRepository.insert(event1)
        eventRepository.delete(event1)
        val result = eventRepository.getAllEvents().first()
        val emptyList: List<TrackrEvent> = emptyList()
        assertEquals(emptyList, result)
    }

    @Test
    fun editPerson() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent(
                person.id,
                0,
                LocalDate.of(2008, 12, 15).toEpochDay(),
                1984,
                7,
                0
        )
        val newPerson = Person(user.id, "tom", "sawyer")
        personDao.insert(newPerson)

        eventRepository.insert(event1)
        eventRepository.editPerson(newPerson, event1)
        val result = eventRepository.getAllEvents().first()
        assertEquals(newPerson.id, result[0].personId)
    }

    @Test
    fun editType() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent(
                person.id,
                0,
                LocalDate.of(2008, 10, 1).toEpochDay(),
                1900,
                1,
                0
        )

        eventRepository.insert(event1)
        eventRepository.editType(1, event1)
        val result = eventRepository.getAllEvents().first()
        assertEquals(1, result[0].type)
    }

    @Test
    fun editDate() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent(
                person.id,
                0,
                LocalDate.of(2008, 10, 1).toEpochDay(),
                1900,
                1,
                0
        )

        eventRepository.insert(event1)
        eventRepository.editDate(LocalDate.of(1980, 11, 2), event1)
        val result = eventRepository.getAllEvents().first()
        assertEquals(LocalDate.of(1980, 11, 2),
                LocalDate.ofEpochDay(result[0].date))
    }

    @Test
    fun editFirstYear() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent(
                person.id,
                0,
                LocalDate.of(2008, 10, 1).toEpochDay(),
                1900,
                1,
                0
        )

        eventRepository.insert(event1)
        eventRepository.editFirstYear(2020, event1)
        val result = eventRepository.getAllEvents().first()
        assertEquals(2020, result[0].firstYear)
    }

    @Test
    fun editInterval() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent(
                person.id,
                0,
                LocalDate.of(2008, 10, 1).toEpochDay(),
                1900,
                1,
                0
        )

        eventRepository.insert(event1)
        eventRepository.editInterval(7, event1)
        val result = eventRepository.getAllEvents().first()
        assertEquals(7, result[0].reminderInterval)
    }

    @Test
    fun deleteAll() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent(
                person.id,
                0,
                LocalDate.of(2008, 10, 1).toEpochDay(),
                1900,
                1,
                0
        )
        val event2 = TrackrEvent(
                person.id,
                0,
                LocalDate.of(2008, 3, 8).toEpochDay(),
                1990,
                7,
                0
        )

        eventRepository.insert(event1)
        eventRepository.insert(event2)
        eventRepository.deleteAll()
        val result = eventRepository.getAllEvents().first()
        val emptyList: List<TrackrEvent> = emptyList()
        assertEquals(emptyList, result)
    }
}