package com.trackr.trackr_app.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trackr.trackr_app.model.User
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
class UserDaoTest {

    private lateinit var userDao: UserDao
    private lateinit var db: TrackrDatabase

    @Before
    fun createDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()

        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java).allowMainThreadQueries().build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetUser() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user)
        val usersFromDatabase = userDao.listUsers().first()
        assertEquals(usersFromDatabase[0].id, user.id)
    }

    @Test
    @Throws(Exception::class)
    fun getAllUsers() = runBlocking {
        val user1 = User("12345678-1234-1234-123456781234", "test")
        val user2 = User("12345678-1234-1234-123456781235", "test2")
        userDao.insert(user1)
        userDao.insert(user2)
        val usersFromDatabase = userDao.listUsers().first()
        assertEquals(usersFromDatabase[0].id, user1.id)
        assertEquals(usersFromDatabase[1].id, user2.id)
    }

    @Test
    @Throws(Exception::class)
    fun updateUsername() = runBlocking {
        val user1 = User("12345678-1234-1234-123456781234", "test")
        userDao.insert(user1)
        userDao.updateUsername("update", user1.id)
        val usersFromDatabase = userDao.listUsers().first()
        assertEquals(usersFromDatabase[0].username, "update")
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() = runBlocking {
        val user1 = User("12345678-1234-1234-123456781234", "test")
        val user2 = User("12345678-1234-1234-123456781235", "test2")
        userDao.insert(user1)
        userDao.insert(user2)
        userDao.deleteAll()
        val usersFromDatabase = userDao.listUsers().first()
        assertTrue(usersFromDatabase.isEmpty())
    }
}