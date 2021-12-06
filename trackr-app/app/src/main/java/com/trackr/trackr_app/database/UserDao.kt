package com.trackr.trackr_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trackr.trackr_app.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    /**
     * List all the users in the database, ordered by their usernames
     *
     * @return a Flow of a list of all Users in the database
     */
    @Query("SELECT * FROM `user-table` ORDER BY username ASC")
    fun listUsers(): Flow<List<User>>

    /**
     * Insert a user into the database, ignoring conflicts
     *
     * @param user The user to add to the database
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    /**
     * Update a users username in the database
     *
     * @param newUsername The username to change to
     * @param userId The ID of the user to modify
     */
    @Query("UPDATE `user-table` SET username = :newUsername WHERE id = :userId")
    suspend fun updateUsername(newUsername: String, userId: String)

    /**
     * Check if a user exists in the database
     *
     * @param username The username of the person to identify
     */
    @Query("SELECT EXISTS(SELECT * FROM `user-table` WHERE username = :username)")
    suspend fun hasUser(username: String): Boolean

    /**
     * Get a user from the database
     *
     * @param username The username of the person to get
     */
    @Query("SELECT * FROM `user-table` WHERE username = :username")
    suspend fun getUser(username: String): User

    /**
     * Delete all users in the database.
     */
    @Query("DELETE FROM `user-table`")
    suspend fun deleteAll()
}