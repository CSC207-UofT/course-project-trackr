package com.trackr.trackr_app.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.model.User
import junit.framework.TestCase.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class EventDaoTest {
    private lateinit var eventDao: EventDao
    private lateinit var personDao: PersonDao
    private lateinit var userDao: UserDao
    private lateinit var db: TrackrDatabase


    //Due to foreign keys the following requires user and person to be working!
    @Before
    fun createDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java).allowMainThreadQueries().build()
        userDao = db.userDao()
        personDao = db.personDao()
        eventDao = db.eventDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetEvent() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user)
        val person = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent("00000000-0000-0000-000000000000",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(1999, 1, 1).toEpochDay().toInt(),
                7,
                0)
        eventDao.insert(event1)
        val eventsFromDatabase = eventDao.listAll().first()
        assertEquals(event1.id, eventsFromDatabase[0].id)
    }

    @Test
    @Throws(Exception::class)
    fun getAllEvents() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user)
        val person = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent("00000000-0000-0000-000000000000",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(1999, 1, 1).toEpochDay().toInt(),
                7,
                0)
        val event2 = TrackrEvent("22222222-2222-2222-222222222222",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(2004, 1, 1).toEpochDay().toInt(),
                3,
                0)
        val event3 = TrackrEvent("33333333-3333-3333-333333333333",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(2004, 4, 1).toEpochDay().toInt(),
                3,
                0)
        eventDao.insert(event1)
        eventDao.insert(event2)
        eventDao.insert(event3)
        val eventsFromDatabase = eventDao.listAll().first()
        assertEquals(eventsFromDatabase[0].id, event3.id)
        assertEquals(eventsFromDatabase[1].id, event2.id)
        assertEquals(eventsFromDatabase[2].id, event1.id)
        assertNotSame(eventsFromDatabase[0], eventsFromDatabase[1])
    }

    @Test
    @Throws(Exception::class)
    fun listFromRange() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user)
        val person = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent("00000000-0000-0000-000000000000",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(2004, 1, 1).toEpochDay().toInt(),
                7,
                0)
        eventDao.insert(event1)
        val event2 = TrackrEvent("22222222-2222-2222-222222222222",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(2004, 1, 2).toEpochDay().toInt(),
                3,
                0)
        eventDao.insert(event2)
        val event3 = TrackrEvent("33333333-3333-3333-333333333333",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(2004, 1, 4).toEpochDay().toInt(),
                3,
                0)
        eventDao.insert(event3)
        val eventsFromDatabase = eventDao.listFromRange(LocalDate.of(2004, 1, 1).toEpochDay().toInt(),
                LocalDate.of(2004, 1, 3).toEpochDay().toInt()).first()
        assertEquals(eventsFromDatabase[0].id, event2.id)
        assertEquals(eventsFromDatabase[1].id, event1.id)
        assertEquals(eventsFromDatabase.size, 2)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user)
        val person = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent("00000000-0000-0000-000000000000",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(1999, 1, 1).toEpochDay().toInt(),
                7,
                0)
        eventDao.insert(event1)
        val event2 = TrackrEvent("22222222-2222-2222-222222222222",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(2004, 1, 1).toEpochDay().toInt(),
                3,
                0)
        eventDao.insert(event2)
        eventDao.deleteAll()
        val eventsFromDatabase = eventDao.listAll().first()
        assertTrue(eventsFromDatabase.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun delete() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user)
        val person = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent("00000000-0000-0000-000000000000",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(1999, 1, 1).toEpochDay().toInt(),
                7,
                0)
        val event2 = TrackrEvent("22222222-2222-2222-222222222222",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(2004, 1, 1).toEpochDay().toInt(),
                3,
                0)
        eventDao.insert(event1)
        eventDao.insert(event2)
        eventDao.delete(event1.id, event1.person_id)
        val eventsFromDatabase = eventDao.listAll().first()
        assertEquals(eventsFromDatabase[0].id, event2.id)
        assertEquals(eventsFromDatabase.size, 1)
    }

    @Test
    @Throws(Exception::class)
    fun editDate() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user)
        val person = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent("00000000-0000-0000-000000000000",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(1999, 1, 1).toEpochDay().toInt(),
                7,
                0)
        eventDao.insert(event1)
        eventDao.editDate(event1.date + 1, event1.id, event1.person_id)
        val eventsFromDatabase = eventDao.listAll().first()
        assertEquals(eventsFromDatabase[0].date, event1.date + 1)
    }

    @Test
    @Throws(Exception::class)
    fun editInterval() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user)
        val person = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent("00000000-0000-0000-000000000000",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(1999, 1, 1).toEpochDay().toInt(),
                7,
                0)
        eventDao.insert(event1)
        eventDao.editInterval(14, event1.id, event1.person_id)
        val eventsFromDatabase = eventDao.listAll().first()
        assertEquals(eventsFromDatabase[0].reminder_interval, 14)
    }

    @Test
    @Throws(Exception::class)
    fun editPerson() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user)
        val person = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob")
        personDao.insert(person)
        val person2 = Person("10101010-1010-1010-101010101010", "12345678-1234-1234-123456781234", "patrick", "star")
        personDao.insert(person2)
        val event1 = TrackrEvent("00000000-0000-0000-000000000000",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(1999, 1, 1).toEpochDay().toInt(),
                7,
                0)
        eventDao.insert(event1)
        eventDao.editPerson(person2.id, event1.id, event1.person_id)
        val eventsFromDatabase = eventDao.listAll().first()
        assertEquals(eventsFromDatabase[0].person_id, person2.id)
    }

    @Test
    @Throws(Exception::class)
    fun editType() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user)
        val person = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent("00000000-0000-0000-000000000000",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(1999, 1, 1).toEpochDay().toInt(),
                7,
                0)
        eventDao.insert(event1)
        eventDao.editType(1, event1.id, event1.person_id)
        val eventsFromDatabase = eventDao.listAll().first()
        assertEquals(eventsFromDatabase[0].type, 1)
    }

    @Test
    @Throws(Exception::class)
    fun confirmDateAccuracy() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user)
        val person = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob")
        personDao.insert(person)
        val event1 = TrackrEvent("00000000-0000-0000-000000000000",
                "01010101-0101-0101-010101010101",
                0,
                LocalDate.of(2004, 7, 16).toEpochDay().toInt(),
                7,
                0)
        eventDao.insert(event1)
        val eventsFromDatabase = eventDao.listAll().first()
        val expected = LocalDate.of(2004, 7, 16)
        val actual = LocalDate.ofEpochDay(eventsFromDatabase[0].date.toLong())
        assertEquals(actual, expected)
    }
}