package com.trackr.trackr_app.usecases

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

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateUsername(new_username: String, user: User) {
        userDao.updateUsername(new_username, user.id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        userDao.deleteAll()
    }
}