package com.trackr.trackr_app.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trackr.trackr_app.model.Event
import java.util.UUID
import java.sql.Date

import kotlinx.coroutines.flow.Flow

interface EventDao {

    @Query("SELECT * FROM `event-table` ORDER BY date DESC")
    fun listAll(): Flow<List<Event>>

    @Query("SELECT * FROM `event-table` WHERE date BETWEEN :start_date AND :end_date ORDER BY date DESC")
    fun listFromRange(start_date: Date, end_date: Date): Flow<List<Event>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event)

    @Query("DELETE FROM `event-table` WHERE id = :id AND person_id = :person_id")
    suspend fun delete(id: UUID, person_id: UUID)

    @Query("UPDATE `event-table` SET person_id = :new_person_id WHERE id = :id AND person_id = :person_id")
    suspend fun editPerson(new_person_id: UUID, id: UUID, person_id: UUID)

    @Query("UPDATE `event-table` SET type = :new_type WHERE id = :id AND person_id = :person_id")
    suspend fun editType(new_type: Int, id: UUID, person_id: UUID)

    @Query("UPDATE `event-table` SET date = :new_date WHERE id = :id AND person_id = :person_id")
    suspend fun editDate(new_date: Date, id: UUID, person_id: UUID)

    @Query("UPDATE `event-table` SET reminder_interval = :new_interval WHERE id = :id AND person_id = :person_id")
    suspend fun editInterval(new_interval: Int, id: UUID, person_id: UUID)

    @Query("DELETE FROM `event-table`")
    suspend fun deleteAll()
}
