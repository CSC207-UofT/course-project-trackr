package com.trackr.trackr_app.manager

import com.trackr.trackr_app.model.User
import com.trackr.trackr_app.repository.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor(
    private val userRepository: UserRepository
) {
    private val defaultUserName = "Default User"

    var currentUser = runBlocking { getDefaultUser() }

    private suspend fun getDefaultUser(): User {
        if (!userRepository.hasUser(defaultUserName)) {
            userRepository.insert(User(defaultUserName))
        }
        return userRepository.getUser(defaultUserName)
    }
}