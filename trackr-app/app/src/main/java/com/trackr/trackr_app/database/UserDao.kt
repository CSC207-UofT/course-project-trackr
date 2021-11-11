package com.trackr.trackr_app.database

import androidx.room.*
import com.trackr.trackr_app.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM `user-table` ORDER BY username ASC")
    fun listUsernames(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("UPDATE `user-table` SET username = :new_username WHERE id = :user_id")
    suspend fun updateUsername(new_username:String, user_id: String)

    @Query("DELETE FROM `user-table`")
    suspend fun deleteAll()
}