package com.trackr.trackr_app.manager

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.media.metrics.Event
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.trackr.trackr_app.database.EventDao
import com.trackr.trackr_app.database.PersonDao
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.database.UserDao
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.model.User
import com.trackr.trackr_app.notification.EventNotificationManager
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class EventManagerTest {
    private lateinit var userDao: UserDao
    private lateinit var eventRepository: EventRepository
    private lateinit var personDao: PersonDao
    private lateinit var db: TrackrDatabase
    private lateinit var eventManager: EventManager

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java)
            .allowMainThreadQueries().build()
        userDao = db.userDao()
        personDao = db.personDao()
        val userRepository = UserRepository(userDao)
        val personRepository = PersonRepository(personDao)
        eventRepository = EventRepository(db.eventDao())
        val eventNotificationManager = EventNotificationManager(context)
        val userManager = UserManager(userRepository)
        val personManager = PersonManager(personRepository, userManager)
        eventManager = EventManager(eventRepository, eventNotificationManager, personManager)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testAddEvent() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "Firstname", "Name")
        personDao.insert(person)
        eventManager.addEvent(person.id, 1, 1, LocalDate.ofEpochDay(100000000))
        val result = eventRepository.getAllEvents().first()
        assertEquals(result[0].personId, person.id)
    }

    @Test
    fun testAddMultipleEventsSamePerson() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "Firstname", "Name")
        personDao.insert(person)
        eventManager.addEvent(person.id, 1, 1, LocalDate.ofEpochDay(100000000))
        eventManager.addEvent(person.id, 0, 1, LocalDate.ofEpochDay(110000000))
        val result = eventRepository.getAllEvents().first()
        assertEquals(result[0].type, 0)
    }


    @Test
    fun testEditReminderInt() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        eventManager.addEvent(person.id, 1, 1, LocalDate.ofEpochDay(100000000))
        val eventID = eventRepository.getAllEvents().first()[0].id
        eventManager.editEvent(eventID, 2, LocalDate.ofEpochDay(100000000), 1)
        val result = eventRepository.getAllEvents().first()
        assertEquals(result[0].reminderInterval, 2)
    }

    @Test
    fun testEditEventTypeExpectBirthday() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        eventManager.addEvent(person.id, 1, 1, LocalDate.ofEpochDay(100000000))
        val eventID = eventRepository.getAllEvents().first()[0].id
        eventManager.editEvent(eventID, 1, LocalDate.ofEpochDay(100000000), 0)
        val result = eventRepository.getAllEvents().first()
        assertEquals(result[0].type, 0)
    }

    @Test
    fun testEditEventTypeExpectAnniversary() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        eventManager.addEvent(person.id, 0, 1, LocalDate.ofEpochDay(100000000))
        val eventID = eventRepository.getAllEvents().first()[0].id
        eventManager.editEvent(eventID, 1, LocalDate.ofEpochDay(100000000), 1)
        val result = eventRepository.getAllEvents().first()
        assertEquals(result[0].type, 1)
    }

    @Test
    fun testEditDate() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        eventManager.addEvent(person.id, 0, 1,
            LocalDate.ofEpochDay(100000000).withYear(2008))
        val eventID = eventRepository.getAllEvents().first()[0].id
        eventManager.editEvent(eventID, 1,
            LocalDate.ofEpochDay(200000000).withYear(2008), 0)
        val result = eventRepository.getAllEvents().first()
        assertEquals(LocalDate.ofEpochDay(result[0].date),
            LocalDate.ofEpochDay(200000000).withYear(2008))
    }

    @Test
    fun testEditNoChange() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        eventManager.addEvent(person.id, 0, 1,
            LocalDate.ofEpochDay(100000000).withYear(2008))
        val eventID = eventRepository.getAllEvents().first()[0].id
        eventManager.editEvent(eventID, 1,
            LocalDate.ofEpochDay(100000000).withYear(2008), 0)
        val result = eventRepository.getAllEvents().first()
        assertEquals(LocalDate.ofEpochDay(result[0].date),
            LocalDate.ofEpochDay(100000000).withYear(2008))
        assertEquals(result[0].type, 0)
        assertEquals(result[0].reminderInterval, 1)
    }

    @Test
    fun testDelete() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        eventManager.addEvent(person.id, 0, 1,
            LocalDate.ofEpochDay(100000000).withYear(2008))
        eventManager.addEvent(person.id, 0, 1,
            LocalDate.ofEpochDay(100000000).withYear(2008))
        val eventID = eventRepository.getAllEvents().first()[0].id
        eventManager.deleteEvent(eventID)
        val result = eventRepository.getAllEvents().first()
        assertNotEquals(result[0].id, eventID)
    }

}