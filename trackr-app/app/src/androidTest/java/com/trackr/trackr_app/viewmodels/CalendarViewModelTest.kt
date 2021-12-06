package com.trackr.trackr_app.viewmodels

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.manager.PersonManager
import com.trackr.trackr_app.manager.UserManager
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class CalendarViewModelTest {
    private lateinit var db: TrackrDatabase
    private lateinit var viewModel: CalendarViewModel

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java)
                .allowMainThreadQueries().build()
        val personRepository = PersonRepository(db.personDao())
        val eventRepository = EventRepository(db.eventDao())
        val userRepository = UserRepository(db.userDao())
        viewModel = CalendarViewModel(
                eventRepository, PersonManager(personRepository,
                UserManager(userRepository)
        ))
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun changeMonth() {
        viewModel.changeMonth(2)
        val result = viewModel.selectedDate.value
        assertEquals(LocalDate.now().plusMonths(2).withDayOfMonth(1), result)
    }

    @Test
    fun changeSelectedDate() {
        viewModel.changeSelectedDate(12)
        val result = viewModel.selectedDate.value
        assertEquals(LocalDate.now().withDayOfMonth(12), result)
    }
}