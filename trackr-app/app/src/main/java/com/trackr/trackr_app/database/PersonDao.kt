package com.trackr.trackr_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trackr.trackr_app.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Query("SELECT * FROM `person-table` ORDER BY first_name, last_name")
    fun listPersons(): Flow<List<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person: Person)

    @Query("DELETE FROM `person-table` WHERE id = :person_id and user_id = :user_id")
    suspend fun delete(person_id: String, user_id: String)

    @Query("UPDATE `person-table`SET first_name = :new_first_name WHERE id= :person_id and user_id = :user_id")
    suspend fun editFirstName(new_first_name: String, person_id: String, user_id: String)

    @Query("UPDATE `person-table`SET last_name = :new_last_name WHERE id= :person_id and user_id = :user_id")
    suspend fun editLastName(new_last_name: String, person_id: String, user_id: String)

    @Query("DELETE FROM `person-table`")
    suspend fun deleteAll()
}
