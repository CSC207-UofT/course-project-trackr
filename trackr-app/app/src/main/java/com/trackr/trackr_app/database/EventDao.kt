package com.trackr.trackr_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trackr.trackr_app.model.TrackrEvent
import java.sql.Date

import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM `event-table` ORDER BY date DESC")
    fun listAll(): Flow<List<TrackrEvent>>

    // range is inclusive.
    @Query("SELECT * FROM `event-table` WHERE date BETWEEN :start_date AND :endDate ORDER BY date DESC")
    fun listFromRange(start_date: Long, endDate: Long): Flow<List<TrackrEvent>>

    @Query("SELECT * FROM `event-table` WHERE date BETWEEN :startDate AND :endDate")
    suspend fun eventsBetweenDate(startDate: Long, endDate: Long): List<TrackrEvent>

    @Query("SELECT * FROM `event-table` WHERE id = :id")
    suspend fun getById(id: String): TrackrEvent

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(trackrEvent: TrackrEvent)

    @Query("DELETE FROM `event-table` WHERE id = :id AND personId = :personId")
    suspend fun delete(id: String, personId: String)

    @Query("UPDATE `event-table` SET personId = :newPersonId WHERE id = :id AND personId = :personId")
    suspend fun editPerson(newPersonId: String, id: String, personId: String)

    @Query("UPDATE `event-table` SET type = :newType WHERE id = :id AND personId = :personId")
    suspend fun editType(newType: Int, id: String, personId: String)

    @Query("UPDATE `event-table` SET date = :newDate WHERE id = :id AND personId = :personId")
    suspend fun editDate(newDate: Long, id: String, personId: String)

    @Query("UPDATE `event-table` SET firstYear = :newYear WHERE id = :id AND personId = :personId")
    suspend fun editFirstYear(newYear: Int, id: String, personId: String)

    @Query("UPDATE `event-table` SET reminderInterval = :newInterval WHERE id = :id AND personId = :personId")
    suspend fun editInterval(newInterval: Int, id: String, personId: String)

    @Query("DELETE FROM `event-table`")
    suspend fun deleteAll()
}
