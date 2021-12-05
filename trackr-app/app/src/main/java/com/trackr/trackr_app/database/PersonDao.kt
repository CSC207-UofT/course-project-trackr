package com.trackr.trackr_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trackr.trackr_app.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Query("SELECT * FROM `person-table` ORDER BY firstName, lastName")
    fun listPersons(): Flow<List<Person>>

    @Query("SELECT * FROM `person-table` WHERE id = :personId")
    suspend fun getPersonById(personId: String): Person

    @Query("SELECT * FROM `person-table` WHERE firstName = :personFirstName and lastName = :personLastName")
    suspend fun getPersonByName(personFirstName: String, personLastName: String): Person

    @Query("SELECT EXISTS(SELECT * FROM `person-table` WHERE firstName = :personFirstName and lastName = :personLastName)")
    suspend fun hasPersonByName(personFirstName: String, personLastName: String): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person: Person)

    @Query("DELETE FROM `person-table` WHERE id = :personId and userId = :userId")
    suspend fun delete(personId: String, userId: String)

    @Query("UPDATE `person-table`SET firstName = :newFirstName WHERE id= :personId and userId = :userId")
    suspend fun editFirstName(newFirstName: String, personId: String, userId: String)

    @Query("UPDATE `person-table`SET lastName = :newLastName WHERE id= :personId and userId = :userId")
    suspend fun editLastName(newLastName: String, personId: String, userId: String)

    @Query("DELETE FROM `person-table`")
    suspend fun deleteAll()
}
