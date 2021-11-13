package com.trackr.trackr_app.viewmodels

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.di.DatabaseModule
import com.trackr.trackr_app.model.User
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class AddScreenViewModelTest : TestCase() {

    private lateinit var viewModel: AddScreenViewModel

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java)
            .allowMainThreadQueries().build()
        val userDao = UserRepository(db.userDao())
        val personDao = PersonRepository(db.personDao())
        val eventDao = EventRepository(db.eventDao())
        viewModel = AddScreenViewModel(eventDao, personDao, userDao)
    }

    @Test
    fun testEditEventName(){
        viewModel.editEventName("Anniversary")
        val result = viewModel.eventName.value
        assertEquals("Anniversary", result)
    }

    @Test
    fun testEditName(){
        viewModel.editName("YourMother")
        val result = viewModel.personName.value
        assertEquals("YourMother", result)
    }

    @Test
    fun testChangeMonth(){
        viewModel.changeMonth("Feb")
        val result = viewModel.eventDate.value
        assertEquals(LocalDate.of(1970, 2, 1), result)
    }

    @Test
    fun testChangeDay(){
        viewModel.changeDay(2)
        val result = viewModel.eventDate.value
        assertEquals(LocalDate.of(1970, 1, 2), result)
    }

    @Test
    fun testChangeReminderInterval(){
        viewModel.changeReminderInterval("3 days before")
        val result = viewModel.chosenReminder.value
        assertEquals("3 days before", result)
    }


}