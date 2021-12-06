package com.trackr.trackr_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trackr.trackr_app.model.TrackrEvent

import kotlinx.coroutines.flow.Flow

/**
 * The interface for event-related
 * manipulations of the database
 */
@Dao
interface EventDao {

    /**
     * List all the events in the database, ordered by date
     *
     * @return a Flow of a list of all Users in the database
     */
    @Query("SELECT * FROM `event-table` ORDER BY date DESC")
    fun listAll(): Flow<List<TrackrEvent>>

    /**
     * Get all events between a date range, inclusive
     *
     * @param startDate The start date of the range
     * @param endDate The end date of the range
     * @return A Flow of a list of TrackrEvents between the date range, inclusive
     */
    @Query("SELECT * FROM `event-table` WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun listFromRange(startDate: Long, endDate: Long): Flow<List<TrackrEvent>>

    /**
     * Get all the events between a date range, inclusive.
     *
     * @param startDate The start date of the range
     * @param endDate The end date of the range
     * @return A list of TrackrEvents between the date range, inclusive
     */
    @Query("SELECT * FROM `event-table` WHERE date BETWEEN :startDate AND :endDate")
    suspend fun eventsBetweenDate(startDate: Long, endDate: Long): List<TrackrEvent>

    /**
     * Get an event by it's ID
     *
     * @param id The event to get
     * @return The requested TrackrEvent
     */
    @Query("SELECT * FROM `event-table` WHERE id = :id")
    suspend fun getById(id: String): TrackrEvent

    /**
     * Get all events by the person they are connected to
     *
     * @param personId The ID of the person to query
     * @return A Flow of a list of TrackrEvents associated with the person
     */
    @Query("SELECT * FROM `event-table` WHERE personId = :personId")
    fun getByPersonId(personId: String): Flow<List<TrackrEvent>>

    /**
     * Insert an event into the database, ignoring conflicts
     *
     * @param trackrEvent The event to insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(trackrEvent: TrackrEvent)

    /**
     * Delete an event
     *
     * @param id The ID of the event to delete
     * @param personId The ID of the person the event is connected to
     */
    @Query("DELETE FROM `event-table` WHERE id = :id AND personId = :personId")
    suspend fun delete(id: String, personId: String)

    /**
     * Edit the person associated with an event
     *
     * @param newPersonId The ID of the person to add to the event
     * @param id The ID of the event to edit
     * @param personId The ID of the person the event is connected to
     */
    @Query("UPDATE `event-table` SET personId = :newPersonId WHERE id = :id AND personId = :personId")
    suspend fun editPerson(newPersonId: String, id: String, personId: String)

    /**
     * Edit the type of an event
     *
     * @param newType The type to change the event to
     * @param id The ID of the event to edit
     * @param personId The ID of the person the event is connected to
     */
    @Query("UPDATE `event-table` SET type = :newType WHERE id = :id AND personId = :personId")
    suspend fun editType(newType: Int, id: String, personId: String)

    /**
     * Edit the date associated with an event
     *
     * @param newDate The date to set the event to
     * @param id The ID of the event to edit
     * @param personId The ID of the person the event is connected to
     */
    @Query("UPDATE `event-table` SET date = :newDate WHERE id = :id AND personId = :personId")
    suspend fun editDate(newDate: Long, id: String, personId: String)

    /**
     * Edit the first year the event should have taken place on
     *
     * @param newYear The new year to set the first year to
     * @param id The ID of the event to edit
     * @param personId The ID of the person the event is connected to
     */
    @Query("UPDATE `event-table` SET firstYear = :newYear WHERE id = :id AND personId = :personId")
    suspend fun editFirstYear(newYear: Int, id: String, personId: String)

    /**
     * Edit the interval at which a person should be reminded of the event
     *
     * @param newInterval The interval to change to
     * @param id The ID of the event to edit
     * @param personId The ID of the person the event is connected to
     */
    @Query("UPDATE `event-table` SET reminderInterval = :newInterval WHERE id = :id AND personId = :personId")
    suspend fun editInterval(newInterval: Int, id: String, personId: String)

    /**
     * Delete all events from the database
     */
    @Query("DELETE FROM `event-table`")
    suspend fun deleteAll()
}
