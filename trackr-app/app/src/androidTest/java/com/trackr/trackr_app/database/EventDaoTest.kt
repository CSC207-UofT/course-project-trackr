package com.trackr.trackr_app.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trackr.trackr_app.model.TrackrEvent
import junit.framework.Assert.*
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
    private lateinit var db: TrackrDatabase


    @Before
    fun createDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java).allowMainThreadQueries().build()
        eventDao = db.eventDao()
    }
    val event1 = TrackrEvent("00000000-0000-0000-000000000000",
            "11111111-1111-1111-111111111111",
            0,
            LocalDate.ofYearDay(1999, 1).toEpochDay().toInt(),
            7,
            0)

    val event2 = TrackrEvent("22222222-2222-2222-222222222222",
            "11111111-1111-1111-111111111112",
            0,
            LocalDate.ofYearDay(2004, 1).toEpochDay().toInt(),
            3,
            0)

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetEvent() = runBlocking {
        eventDao.insert(event1)
        val eventsFromDatabase = eventDao.listAll().first()
        assertEquals(event1.id, eventsFromDatabase[0].id)
    }

    fun getAllEvents() = runBlocking {
        eventDao.insert(event1)
        eventDao.insert(event2)
        val eventsFromDatabase = eventDao.listAll().first()
        assertEquals(eventsFromDatabase[0].id, event1.id)
        assertEquals(eventsFromDatabase[1].id, event2.id)
        assertNotSame(eventsFromDatabase[0], eventsFromDatabase[1])
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() = runBlocking {
        eventDao.insert(event1)
        eventDao.insert(event2)
        eventDao.deleteAll()
        val eventsFromDatabase = eventDao.listAll().first()
        assertTrue(eventsFromDatabase.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun editDate() = runBlocking {
        eventDao.insert(event1)
        eventDao.editDate(event1.date + 1, event1.id, event1.person_id)
        val eventsFromDatabase = eventDao.listAll().first()
        assertNotSame(eventsFromDatabase[0].date, event1.date)
    }

    @Test
    @Throws(Exception::class)
    fun editInterval() = runBlocking {
        eventDao.insert(event1)
        eventDao.editDate(event1.date + 1, event1.id, event1.person_id)
        val eventsFromDatabase = eventDao.listAll().first()
        assertNotSame(eventsFromDatabase[0].date, event1.date)
    }

    @Test
    @Throws(Exception::class)
    fun editPerson() = runBlocking {
        eventDao.insert(event1)
        val new = "33333333-3333-3333-333333333333"
        eventDao.editPerson(new, event1.id, event1.person_id)
        val eventsFromDatabase = eventDao.listAll().first()
        assertEquals(eventsFromDatabase[0].person_id, new)
    }

    @Test
    @Throws(Exception::class)
    fun editType() = runBlocking {
        eventDao.insert(event1)
        eventDao.editType(1, event1.id, event1.person_id)
        val eventsFromDatabase = eventDao.listAll().first()
        assertNotSame(eventsFromDatabase[0].type, 1)
    }
}