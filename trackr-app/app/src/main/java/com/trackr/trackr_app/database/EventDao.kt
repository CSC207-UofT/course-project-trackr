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
    @Query("SELECT * FROM `event-table` WHERE date BETWEEN :start_date AND :end_date ORDER BY date DESC")
    fun listFromRange(start_date: Long, end_date: Long): Flow<List<TrackrEvent>>

    @Query("SELECT * FROM `event-table` WHERE id = :id")
    suspend fun getById(id: String): TrackrEvent

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(trackrEvent: TrackrEvent)

    @Query("DELETE FROM `event-table` WHERE id = :id AND person_id = :person_id")
    suspend fun delete(id: String, person_id: String)

    @Query("UPDATE `event-table` SET person_id = :new_person_id WHERE id = :id AND person_id = :person_id")
    suspend fun editPerson(new_person_id: String, id: String, person_id: String)

    @Query("UPDATE `event-table` SET type = :new_type WHERE id = :id AND person_id = :person_id")
    suspend fun editType(new_type: Int, id: String, person_id: String)

    @Query("UPDATE `event-table` SET date = :new_date WHERE id = :id AND person_id = :person_id")
    suspend fun editDate(new_date: Long, id: String, person_id: String)

    @Query("UPDATE `event-table` SET firstYear = :new_year WHERE id = :id AND person_id = :person_id")
    suspend fun editFirstYear(new_year: Int, id: String, person_id: String)

    @Query("UPDATE `event-table` SET reminder_interval = :new_interval WHERE id = :id AND person_id = :person_id")
    suspend fun editInterval(new_interval: Int, id: String, person_id: String)

    @Query("DELETE FROM `event-table`")
    suspend fun deleteAll()
}
