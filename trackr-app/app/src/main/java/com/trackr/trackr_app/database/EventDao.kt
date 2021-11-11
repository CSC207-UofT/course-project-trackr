package com.trackr.trackr_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trackr.trackr_app.model.Event
import java.sql.Date

import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM `event-table` ORDER BY date DESC")
    fun listAll(): Flow<List<Event>>

    @Query("SELECT * FROM `event-table` WHERE date BETWEEN :start_date AND :end_date ORDER BY date ASC")
    fun listFromRange(start_date: Int, end_date: Int): Flow<List<Event>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event)

    @Query("DELETE FROM `event-table` WHERE id = :id AND person_id = :person_id")
    suspend fun delete(id: String, person_id: String)

    @Query("UPDATE `event-table` SET person_id = :new_person_id WHERE id = :id AND person_id = :person_id")
    suspend fun editPerson(new_person_id: String, id: String, person_id: String)

    @Query("UPDATE `event-table` SET type = :new_type WHERE id = :id AND person_id = :person_id")
    suspend fun editType(new_type: Int, id: String, person_id: String)

    @Query("UPDATE `event-table` SET date = :new_date WHERE id = :id AND person_id = :person_id")
    suspend fun editDate(new_date: Int, id: String, person_id: String)

    @Query("UPDATE `event-table` SET reminder_interval = :new_interval WHERE id = :id AND person_id = :person_id")
    suspend fun editInterval(new_interval: Int, id: String, person_id: String)

    @Query("DELETE FROM `event-table`")
    suspend fun deleteAll()
}
