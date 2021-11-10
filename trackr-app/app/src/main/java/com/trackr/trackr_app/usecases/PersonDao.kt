package com.trackr.trackr_app.usecases

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trackr.trackr_app.entities.Person
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface PersonDao {

    @Query("SELECT * FROM `person-table` ORDER BY first_name, last_name")
    fun listpersons(): Flow<List<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person: Person)

    @Query("DELETE FROM `person-table` WHERE id = :person_id and user_id = :user_id")
    suspend fun delete(person_id: UUID, user_id: UUID)

    @Query("UPDATE `person-table`SET first_name = :new_first_name WHERE id= :person_id and user_id = :user_id")
    suspend fun editFirstName(new_first_name: String, person_id: UUID, user_id: UUID)

    @Query("UPDATE `person-table`SET last_name = :new_last_name WHERE id= :person_id and user_id = :user_id")
    suspend fun editLastName(new_last_name: String, person_id: UUID, user_id: UUID)

    @Query("DELETE FROM `person-table`")
    suspend fun deleteAll()
}
