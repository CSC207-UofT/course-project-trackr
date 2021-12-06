package com.trackr.trackr_app.manager

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.trackr.trackr_app.database.PersonDao
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.database.UserDao
import com.trackr.trackr_app.model.User
import com.trackr.trackr_app.notification.EventNotificationManager
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class PersonManagerTest {
    private lateinit var userDao: UserDao
    private lateinit var eventRepository: EventRepository
    private lateinit var personDao: PersonDao
    private lateinit var db: TrackrDatabase
    private lateinit var eventManager: EventManager
    private lateinit var personManager: PersonManager

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
        personManager = PersonManager(personRepository, userManager)
        eventManager = EventManager(eventRepository, eventNotificationManager, personManager)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testAddPerson() = runBlocking {
        val newPerson = personManager.createPerson("FirstName", "LastName")
        val result = personManager.getPersonById(newPerson.id).id
        assertEquals(result, newPerson.id)
    }

    @Test
    fun testEditPersonLastName() = runBlocking {
        val newPerson = personManager.createPerson("FirstName", "LastName")
        personManager.editPerson(newPerson.id, firstName = "FirstName", lastName = "Hello")
        val result = personManager.getPersonById(newPerson.id).lastName
        assertEquals(result, "Hello")
    }

    @Test
    fun testEditPersonFirstName() = runBlocking {
        val newPerson = personManager.createPerson("FirstName", "LastName")
        personManager.editPerson(newPerson.id, firstName = "Hello", lastName = "LastName")
        val result = personManager.getPersonById(newPerson.id).firstName
        assertEquals(result, "Hello")
    }

    @Test
    fun testEditPersonFullName() = runBlocking {
        val newPerson = personManager.createPerson("FirstName", "LastName")
        personManager.editPerson(newPerson.id, firstName = "Hello", lastName = "Hello")
        val result = personManager.getPersonById(newPerson.id)
        assertEquals(result.firstName, "Hello")
        assertEquals(result.lastName, "Hello")
    }
}