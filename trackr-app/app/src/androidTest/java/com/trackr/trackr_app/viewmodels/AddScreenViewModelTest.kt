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
import com.trackr.trackr_app.model.User
import com.trackr.trackr_app.notification.EventNotificationManager
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class AddScreenViewModelTest : TestCase() {
    private lateinit var db: TrackrDatabase
    private lateinit var eventRepository: EventRepository
    private lateinit var viewModel: AddScreenViewModel

    @Before
    public override fun setUp() = runBlocking {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java)
            .allowMainThreadQueries().build()
        val userRepository = UserRepository(db.userDao())
        val personRepository = PersonRepository(db.personDao())
        val userManager = UserManager(userRepository)
        eventRepository = EventRepository(db.eventDao())
        val eventNotificationManager = EventNotificationManager(context)

        val user = User("test")
        userRepository.insert(user)
        val person = Person(user.id, "tom", "sawyer")
        personRepository.insert(person)

        val state = SavedStateHandle()
        state.set("personId", person.id)

        viewModel = AddScreenViewModel(
                PersonManager(personRepository, userManager),
                EventManager(eventRepository, eventNotificationManager,
                        PersonManager(personRepository, userManager)),
                state
        )
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
        viewModel.addEvent().join()
        val result = eventRepository.getAllEvents().first()
        assertNotNull(result[0])
    }
}