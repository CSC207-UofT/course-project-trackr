package com.trackr.trackr_app.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.database.UserDao
import com.trackr.trackr_app.model.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class UserRepositoryTest {
    private lateinit var userRepository: UserRepository
    private lateinit var userDao: UserDao
    private lateinit var db: TrackrDatabase

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()

        db = Room.inMemoryDatabaseBuilder(context, TrackrDatabase::class.java)
                .allowMainThreadQueries().build()
        userDao = db.userDao()
        userRepository = UserRepository(userDao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insert() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userRepository.insert(user)
        val result = userDao.listUsers().first()[0].id
        assertEquals(user.id, result)
    }

    @Test
    fun updateUsername() = runBlocking {
        val user = User("12345678-1234-1234-123456781234", "test")
        userRepository.insert(user)
        userRepository.updateUsername("new username", user)
        val result = userRepository.allUsers.first()[0].username
        assertEquals("new username", result)
    }

    @Test
    fun deleteAll() = runBlocking {
        val user1 = User("12345678-1234-1234-123456781234", "test")
        val user2 = User("00000000-1234-1234-111156781234", "test2")
        userRepository.insert(user1)
        userRepository.insert(user2)
        userRepository.deleteAll()
        val result = userRepository.allUsers.first()
        val expected: List<User> = emptyList()
        assertEquals(expected, result)
    }
}