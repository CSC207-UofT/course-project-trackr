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

    @Query("UPDATE `user-table` SET username = :new_username WHERE id = :userId")
    suspend fun updateUsername(new_username:String, userId: String)

    @Query("SELECT EXISTS(SELECT * FROM `user-table` WHERE username = :username)")
    suspend fun hasUser(username:String): Boolean

    @Query("SELECT * FROM `user-table` WHERE username = :username")
    suspend fun getUser(username:String): User

    @Query("DELETE FROM `user-table`")
    suspend fun deleteAll()
}