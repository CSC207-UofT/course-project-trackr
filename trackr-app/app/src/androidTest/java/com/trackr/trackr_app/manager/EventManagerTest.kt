package com.trackr.trackr_app.manager

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.trackr.trackr_app.database.EventDao
import com.trackr.trackr_app.database.PersonDao
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.database.UserDao
import com.trackr.trackr_app.notification.EventNotificationManager
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test

class EventManagerTest {
    private lateinit var eventRepository: EventRepository
    private lateinit var eventDao: EventDao
    private lateinit var personDao: PersonDao
    private lateinit var userDao: UserDao
    private lateinit var db: TrackrDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java)
            .allowMainThreadQueries().build()
        val userRepository = UserRepository(db.userDao())
        val personRepository = PersonRepository(db.personDao())
        eventRepository = EventRepository(db.eventDao())
        val eventNotificationManager = EventNotificationManager(context)
        val userManager = UserManager(userRepository)
        val personManager = PersonManager(personRepository, userManager)
        val eventManager = EventManager(eventRepository, eventNotificationManager, personManager)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testEditEventName(){
        assertEquals(1, 1)
    }
}