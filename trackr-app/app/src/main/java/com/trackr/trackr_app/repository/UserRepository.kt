package com.trackr.trackr_app.repository

import androidx.annotation.WorkerThread
import com.trackr.trackr_app.database.UserDao
import com.trackr.trackr_app.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao: UserDao) {
    val allUsers: Flow<List<User>> = userDao.listUsers()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateUsername(newUsername: String, user: User) {
        userDao.updateUsername(newUsername, user.id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun hasUser(username: String): Boolean {
        return userDao.hasUser(username)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getUser(username: String): User {
        return userDao.getUser(username)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        userDao.deleteAll()
    }
}