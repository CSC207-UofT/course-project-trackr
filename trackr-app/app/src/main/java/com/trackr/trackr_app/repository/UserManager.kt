package com.trackr.trackr_app.repository

import com.trackr.trackr_app.model.User
import java.util.UUID


class UserManager {
}
fun createUser(username: String): User {
    return User(UUID.randomUUID().toString(), username)
}