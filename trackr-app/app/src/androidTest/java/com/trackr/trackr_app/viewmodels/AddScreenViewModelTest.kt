package com.trackr.trackr_app.viewmodels

import android.content.Context
import android.media.metrics.Event
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.asLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trackr.trackr_app.database.EventDao
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.di.DatabaseModule
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.model.User
import com.trackr.trackr_app.notification.EventNotificationManager
import com.trackr.trackr_app.notification.EventNotificationManager_Factory
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class AddScreenViewModelTest : TestCase() {
    private lateinit var db: TrackrDatabase
    private lateinit var eventRepository: EventRepository
    private lateinit var viewModel: AddScreenViewModel

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java)
            .allowMainThreadQueries().build()
        val userRepository = UserRepository(db.userDao())
        val personRepository = PersonRepository(db.personDao())
        eventRepository = EventRepository(db.eventDao())
        val eventNotificationManager = EventNotificationManager(context)
        viewModel = AddScreenViewModel(
                eventRepository, personRepository, userRepository, eventNotificationManager)
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        db.close()
    }

    @Test
    fun testEditFirstName(){
        viewModel.editFirstName("Your")
        val result = viewModel.firstName.value
        assertEquals("Your", result)
    }

    @Test
    fun testEditLastName(){
        viewModel.editLastName("Mom")
        val result = viewModel.lastName.value
        assertEquals("Mom", result)
    }

    @Test
    fun testEditEventName(){
        viewModel.editEventName("Anniversary")
        val result = viewModel.eventName.value
        assertEquals("Anniversary", result)
    }


    @Test
    fun testChangeMonth(){
        viewModel.changeMonth("Feb")
        val result = viewModel.eventDate.value
        assertEquals(LocalDate.now().withMonth(2), result)
    }

    @Test
    fun testChangeDay(){
        viewModel.changeDay(2)
        val result = viewModel.eventDate.value
        assertEquals(LocalDate.now().withDayOfMonth(2), result)
    }

    @Test
    fun testChangeYear(){
        viewModel.changeYear(2022)
        val result = viewModel.eventDate.value
        assertEquals(LocalDate.now().withYear(2022), result)
    }

    @Test
    fun testChangeReminderInterval(){
        viewModel.changeReminderInterval("3 days before")
        val result = viewModel.chosenReminder.value
        assertEquals("3 days before", result)
    }

    @Test
    fun testAddEvent() = runBlocking {
        viewModel.addEvent()
        val result = eventRepository.allEvents.first()
        assertNotNull(result[0])
    }


}