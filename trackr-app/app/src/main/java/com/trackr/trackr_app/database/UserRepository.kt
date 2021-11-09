package com.trackr.trackr_app.database

import androidx.annotation.WorkerThread
import com.trackr.trackr_app.entities.User
import kotlinx.coroutines.flow.Flow


class UserRepository(private val userDao: UserDao) {
    val allUsers: Flow<List<User>> = userDao.listUsernames()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }
}