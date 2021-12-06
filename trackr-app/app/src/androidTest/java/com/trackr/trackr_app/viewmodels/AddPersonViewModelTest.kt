package com.trackr.trackr_app.viewmodels

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.manager.PersonManager
import com.trackr.trackr_app.manager.UserManager
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddPersonViewModelTest {
    private lateinit var db: TrackrDatabase
    private lateinit var personRepository: PersonRepository
    private lateinit var viewModel: AddPersonViewModel

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java)
                .allowMainThreadQueries().build()
        val userRepository = UserRepository(db.userDao())
        personRepository = PersonRepository(db.personDao())
        val userManager = UserManager(userRepository)
        val personManager = PersonManager(personRepository, userManager)
        viewModel = AddPersonViewModel(personManager)
    }

    @Test
    fun editFirstName() = runBlocking {
        viewModel.editFirstName("John").join()

        val result = viewModel.firstName.value
        val expected = "John"

        assertEquals(expected, result)
    }

    @Test
    fun editLastName() = runBlocking {
        viewModel.editLastName("Xina").join()

        val result = viewModel.lastName.value
        val expected = "Xina"

        assertEquals(expected, result)
    }

    @Test
    fun addPerson() = runBlocking {
        viewModel.editFirstName("John").join()
        viewModel.editLastName("Xina").join()
        viewModel.addPerson().join()

        val result = personRepository.getAllPersons().first()[0]
        assertEquals("John", result.firstName)
        assertEquals("Xina", result.lastName)
    }
}