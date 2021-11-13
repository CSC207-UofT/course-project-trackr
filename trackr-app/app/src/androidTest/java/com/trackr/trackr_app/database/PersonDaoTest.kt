package com.trackr.trackr_app.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.User
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.lang.Exception

class PersonDaoTest {
    private lateinit var userDao: UserDao
    private lateinit var personDao: PersonDao
    private lateinit var db: TrackrDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java).allowMainThreadQueries().build()
        userDao = db.userDao()
        personDao = db.personDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetPerson() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user)
        val person = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob")
        personDao.insert(person)
        val personsFromDatabase = personDao.listPersons().first()
        assertEquals(personsFromDatabase[0].id, person.id)
    }

    @Test
    @Throws(Exception::class)
    fun getAllPersons() = runBlocking {
        val user1 = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user1)
        val person1 = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob" )
        personDao.insert(person1)
        val person2 = Person("10101010-1010-1010-101010101010", "12345678-1234-1234-123456781234", "patrick", "star")
        personDao.insert(person2)
        val personsFromDatabase = personDao.listPersons().first()
        // the order changes because listPersons sorts by first name and then last name
        assertEquals(personsFromDatabase[0].id, person2.id)
        assertEquals(personsFromDatabase[1].id, person1.id)
    }

    @Test
    @Throws(Exception::class)
    fun editFirstName() = runBlocking {
        val user1 = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user1)
        val person1 = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob" )
        personDao.insert(person1)
        personDao.editFirstName("spongier", person1.id, user1.id)
        val personsFromDatabase = personDao.listPersons().first()
        assertEquals(personsFromDatabase[0].first_name, "spongier")
    }

    @Test
    @Throws(Exception::class)
    fun editLastName() = runBlocking {
        val user1 = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user1)
        val person1 = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob" )
        personDao.insert(person1)
        personDao.editLastName("bobert", person1.id, user1.id)
        val personsFromDatabase = personDao.listPersons().first()
        assertEquals(personsFromDatabase[0].last_name, "bobert")
    }

    @Test
    @Throws(Exception::class)
    fun delete() = runBlocking {
        val user1 = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user1)
        val person1 = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob" )
        personDao.insert(person1)
        val person2 = Person("10101010-1010-1010-101010101010", "12345678-1234-1234-123456781234", "patrick", "star")
        personDao.insert(person2)
        personDao.delete(person1.id, person1.user_id)
        val personsFromDatabase = personDao.listPersons().first()
        assertEquals(personsFromDatabase[0].id, person2.id)
        assertEquals(personsFromDatabase.size, 1)
        
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() = runBlocking {
        val user1 = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user1)
        val person1 = Person("01010101-0101-0101-010101010101", "12345678-1234-1234-123456781234", "sponge", "bob" )
        personDao.insert(person1)
        val person2 = Person("10101010-1010-1010-101010101010", "12345678-1234-1234-123456781234", "patrick", "star")
        personDao.insert(person2)
        personDao.deleteAll()
        val personsFromDatabase = personDao.listPersons().first()
        assertTrue(personsFromDatabase.isEmpty())
    }
}