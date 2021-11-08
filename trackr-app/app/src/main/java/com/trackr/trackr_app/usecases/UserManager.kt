package com.trackr.trackr_app.usecases

import com.trackr.trackr_app.entities.User
import java.util.UUID

/**
 * UserManager manages users
 */
class UserManager {
    /**
     * Create a new user
     * @param id - unique user identifier
     * @param username - whatever the user wants to call themselves
     */
    fun createUser(
            id: UUID,
            username: String,
    ): User {
        return User(id, username)
    }

    /**
     * Return a new User object with the same uuid but a different username
     * @param user - the user to edit
     * @param new_username - the new username
     */
    fun editUsername(
            user: User,
            new_username: String,
    ): User {
        return User(user.id, new_username)
    }

}