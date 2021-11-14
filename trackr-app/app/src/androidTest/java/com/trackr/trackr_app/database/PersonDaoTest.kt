package com.trackr.trackr_app.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
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
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

// Tests were based off of the Android Room DB codelabs repo:
// https://github.com/googlecodelabs/android-room-with-a-view/blob/kotlin/app/src/androidTest/java/com/example/android/roomwordssample/WordDaoTest.kt
@RunWith(AndroidJUnit4::class)
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
        val user = User("test")
        userDao.insert(user)
        val person = Person(user.id, "sponge", "bob")
        personDao.insert(person)
        val personsFromDatabase = personDao.listPersons().first()
        assertEquals(personsFromDatabase[0].id, person.id)
    }

    @Test
    @Throws(Exception::class)
    fun getAllPersons() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person1 = Person(user.id, "sponge", "bob")
        personDao.insert(person1)
        val person2 = Person(user.id, "patrick", "star")
        personDao.insert(person2)
        val personsFromDatabase = personDao.listPersons().first()
        // the order changes because listPersons sorts by first name and then last name
        assertEquals(personsFromDatabase[0].id, person2.id)
        assertEquals(personsFromDatabase[1].id, person1.id)
    }

    @Test
    @Throws(Exception::class)
    fun editFirstName() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person1 = Person(user.id, "sponge", "bob")
        personDao.insert(person1)
        personDao.editFirstName("spongier", person1.id, user.id)
        val personsFromDatabase = personDao.listPersons().first()
        assertEquals(personsFromDatabase[0].first_name, "spongier")
    }

    @Test
    @Throws(Exception::class)
    fun editLastName() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person1 = Person(user.id, "sponge", "bob")
        personDao.insert(person1)
        personDao.editLastName("bobert", person1.id, user.id)
        val personsFromDatabase = personDao.listPersons().first()
        assertEquals(personsFromDatabase[0].last_name, "bobert")
    }

    @Test
    @Throws(Exception::class)
    fun delete() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person1 = Person(user.id, "sponge", "bob")
        personDao.insert(person1)
        val person2 = Person(user.id, "patrick", "star")
        personDao.insert(person2)
        personDao.delete(person1.id, person1.user_id)
        val personsFromDatabase = personDao.listPersons().first()
        assertEquals(personsFromDatabase[0].id, person2.id)
        assertEquals(personsFromDatabase.size, 1)
        
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() = runBlocking {
        val user = User("test")
        userDao.insert(user)
        val person1 = Person(user.id, "sponge", "bob")
        personDao.insert(person1)
        val person2 = Person(user.id, "patrick", "star")
        personDao.insert(person2)
        personDao.deleteAll()
        val personsFromDatabase = personDao.listPersons().first()
        assertTrue(personsFromDatabase.isEmpty())
    }
}