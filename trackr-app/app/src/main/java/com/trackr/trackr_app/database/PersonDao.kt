package com.trackr.trackr_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trackr.trackr_app.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    /**
     * List all the people in the database, ordered by date
     */
    @Query("SELECT * FROM `person-table` ORDER BY firstName, lastName")
    fun listPersons(): Flow<List<Person>>

    /**
     * Get a person from the database by their ID
     *
     * @param personId The ID of the person to get
     * @return The requested Person object
     */
    @Query("SELECT * FROM `person-table` WHERE id = :personId")
    suspend fun getPersonById(personId: String): Person

    /**
     * Get a person from the database by their first and last names
     *
     * @param personFirstName The first name of the person to search for
     * @param personLastName The last name of the person to search for
     * @return The requested Person object
     */
    @Query("SELECT * FROM `person-table` WHERE firstName = :personFirstName and lastName = :personLastName")
    suspend fun getPersonByName(personFirstName: String, personLastName: String): Person

    /**
     * Check if a person exists in the database by their first and last names
     *
     * @param personFirstName The first name of the person to search for
     * @param personLastName The last name of the person to search for
     * @return True if the person exists, False if it does not
     */
    @Query("SELECT EXISTS(SELECT * FROM `person-table` WHERE firstName = :personFirstName and lastName = :personLastName)")
    suspend fun hasPersonByName(personFirstName: String, personLastName: String): Boolean

    /**
     * Add a person object into the database, excluding duplicates
     *
     * @param person The person to add
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person: Person)

    /**
     * Delete a person from the database
     *
     * @param personId The ID of the person to delete
     * @param userId The user ID connected to that person
     */
    @Query("DELETE FROM `person-table` WHERE id = :personId and userId = :userId")
    suspend fun delete(personId: String, userId: String)

    /**
     * Edit a person's first name in the database
     *
     * @param newFirstName The name to change the first name to
     * @param personId The ID of the person to delete
     * @param userId The user ID connected to that person
     */
    @Query("UPDATE `person-table`SET firstName = :newFirstName WHERE id= :personId and userId = :userId")
    suspend fun editFirstName(newFirstName: String, personId: String, userId: String)

    /**
     * Edit a person's last name in the database
     *
     * @param newLastName The name to change the last name to
     * @param personId The ID of the person to delete
     * @param userId The user ID connected to that person
     */
    @Query("UPDATE `person-table`SET lastName = :newLastName WHERE id= :personId and userId = :userId")
    suspend fun editLastName(newLastName: String, personId: String, userId: String)

    /**
     * Delete all persons from the database
     */
    @Query("DELETE FROM `person-table`")
    suspend fun deleteAll()
}
