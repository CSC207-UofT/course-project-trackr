package com.trackr.trackr_app.database

import androidx.room.*
import com.trackr.trackr_app.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM `user-table` ORDER BY username ASC")
    fun listUsers(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("UPDATE `user-table` SET username = :new_username WHERE id = :user_id")
    suspend fun updateUsername(new_username:String, user_id: String)

    @Query("SELECT EXISTS(SELECT 1 FROM `user-table` WHERE username = :username)")
    suspend fun hasUser(username:String): Boolean

    @Query("SELECT 1 FROM `user-table` WHERE username = :username")
    suspend fun getUser(username:String): User

    @Query("DELETE FROM `user-table`")
    suspend fun deleteAll()
}