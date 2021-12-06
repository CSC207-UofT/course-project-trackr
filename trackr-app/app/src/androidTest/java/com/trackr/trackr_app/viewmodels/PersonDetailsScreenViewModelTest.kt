package com.trackr.trackr_app.viewmodels

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.manager.PersonManager
import com.trackr.trackr_app.manager.UserManager
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.User
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PersonDetailsScreenViewModelTest {
    private lateinit var db: TrackrDatabase
    private lateinit var person: Person
    private lateinit var personRepository: PersonRepository
    private lateinit var viewModel: PersonDetailsScreenViewModel

    @Before
    fun setUp() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java)
                .allowMainThreadQueries().build()
        val userRepository = UserRepository(db.userDao())
        personRepository = PersonRepository(db.personDao())
        val eventRepository = EventRepository(db.eventDao())
        val userManager = UserManager(userRepository)
        val personManager = PersonManager(personRepository, userManager)

        val user = User("test")
        userRepository.insert(user)
        person = Person(user.id, "tom", "sawyer")
        personRepository.insert(person)

        val state = SavedStateHandle()
        state.set("personId", person.id)

        viewModel = PersonDetailsScreenViewModel(personManager,
                eventRepository,
                personManager,
                state)
    }

    @Test
    fun editFirstName() = runBlocking {
        viewModel.editFirstName("Timothy").join()

        val result = viewModel.firstName.value
        val expected = "Timothy"

        assertEquals(expected, result)
    }

    @Test
    fun editLastName() = runBlocking {
        viewModel.editLastName("Laurelle").join()

        val result = viewModel.lastName.value
        val expected = "Laurelle"

        assertEquals(expected, result)
    }

    @Test
    fun editPerson() = runBlocking {
        viewModel.editFirstName("Timothy").join()
        viewModel.editLastName("Laurelle").join()
        viewModel.editPerson().join()

        val result = personRepository.getAllPersons().first()[0]
        assertEquals("Timothy", result.firstName)
        assertEquals("Laurelle", result.lastName)
    }

    @Test
    fun deletePerson() = runBlocking {
        viewModel.deletePerson().join()

        val result = personRepository.getAllPersons().first()
        val expected: List<Person> = emptyList()

        assertEquals(expected, result)
    }
}