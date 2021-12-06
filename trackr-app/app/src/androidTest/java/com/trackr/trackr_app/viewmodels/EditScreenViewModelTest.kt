package com.trackr.trackr_app.viewmodels

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.manager.EventManager
import com.trackr.trackr_app.manager.PersonManager
import com.trackr.trackr_app.manager.UserManager
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.model.User
import com.trackr.trackr_app.notification.EventNotificationManager
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class EditScreenViewModelTest {
    private lateinit var db: TrackrDatabase
    private lateinit var event: TrackrEvent
    private lateinit var person: Person
    private lateinit var eventRepository: EventRepository
    private lateinit var viewModel: EditScreenViewModel

    @Before
    fun setUp() = runBlocking {
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

        val defaultUser = User("Default User")
        userRepository.insert(defaultUser)

        person = Person(
                userId = defaultUser.id,
                firstName = "jon",
                lastName = "d")
        personRepository.insert(person)

        event = TrackrEvent(
                person.id,
                0,
                LocalDate.now().withYear(1970).toEpochDay(),
                LocalDate.now().year,
                1
        )
        eventRepository.insert(event)

        val state = SavedStateHandle()
        state.set("eventId", event.id)

        viewModel = EditScreenViewModel(eventManager, eventManager, state)
    }

    @Test
    fun editEventName() {
        viewModel.editEventName("Anniversary")
        val result = viewModel.eventName.value
        assertEquals("Anniversary", result)
    }

    @Test
    fun changeMonth() {
        viewModel.changeMonth("Jun")
        val result = viewModel.eventDate.value
        assertEquals(LocalDate.of(1970, 6, 1).month, result.month)
    }

    @Test
    fun changeDay() {
        viewModel.changeDay(5)
        val result = viewModel.eventDate.value
        assertEquals(5, result.dayOfMonth)
    }

    @Test
    fun changeYear() {
        viewModel.changeYear(2022)
        val result = viewModel.eventDate.value
        assertEquals(2022, result.year)
    }

    @Test
    fun changeReminderInterval() {
        viewModel.changeReminderInterval("1 week before")
        val result = viewModel.chosenReminder.value
        assertEquals("1 week before", result)
    }

    @Test
    fun editEvent() = runBlocking {
        viewModel.editEventName("Anniversary")
        viewModel.editEvent().join()
        val expected = TrackrEvent(
                person.id,
                1,
                LocalDate.now().withYear(1970).toEpochDay(),
                2020,
                1
        )
        val result = eventRepository.getById(event.id)

        assertNotEquals(event.type, result.type)
        assertEquals(expected.type, result.type)
    }

    @Test
    fun deleteEvent() = runBlocking {
        viewModel.deleteEvent().join()
        val expected: List<TrackrEvent> = emptyList()
        val result = eventRepository.getAllEvents().first()
        assertEquals(expected, result)
    }
}