package com.trackr.trackr_app.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.trackr.trackr_app.database.PersonDao
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.database.UserDao
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class PersonRepositoryTest {
    private lateinit var personRepository: PersonRepository
    private lateinit var userDao: UserDao
    private lateinit var personDao: PersonDao
    private lateinit var db: TrackrDatabase

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java).allowMainThreadQueries().build()
        userDao = db.userDao()
        personDao = db.personDao()
        personRepository = PersonRepository(personDao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun getPersonById() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "tom", "sawyer")
        personDao.insert(person)
        val result = personRepository.getPersonById(person.id)
        assertEquals(person.id, result.id)
    }

    @Test
    fun insert() = runBlocking{
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "tom", "sawyer")
        personRepository.insert(person)
        val result = personRepository.allPersons.first()
        assertEquals(person.id, result[0].id)
    }

    @Test
    fun delete() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "tom", "sawyer")
        personRepository.insert(person)
        personRepository.delete(person)
        val result = personRepository.allPersons.first()
        val expected: List<Person> = emptyList()
        assertEquals(expected, result)
    }

    @Test
    fun editFirstName() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "tom", "sawyer")
        personRepository.insert(person)
        personRepository.editFirstName("Huckleberry", person)
        val result = personRepository.allPersons.first()[0].firstName
        val expected = "Huckleberry"
        assertEquals(expected, result)
    }

    @Test
    fun editLastName() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "tom", "Fin")
        personRepository.insert(person)
        personRepository.editLastName("Fin", person)
        val result = personRepository.allPersons.first()[0].lastName
        val expected = "Fin"
        assertEquals(expected, result)
    }

    @Test
    fun deleteAll() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person1 = Person(user.id, "tom", "sawyer")
        val person2 = Person(user.id, "jason", "derulo")
        personRepository.insert(person1)
        personRepository.insert(person2)
        personRepository.deleteAll()
        val result = personRepository.allPersons.first()
        val expected: List<Person> = emptyList()
        assertEquals(expected, result)
    }
}