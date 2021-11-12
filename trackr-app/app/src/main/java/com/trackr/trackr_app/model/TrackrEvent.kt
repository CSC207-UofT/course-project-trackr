package com.trackr.trackr_app.model

import androidx.room.*
import java.time.LocalDate

@Entity(tableName = "event-table",
        // Establish foreign key relation
        foreignKeys = [
            ForeignKey(entity = Person::class,
                    parentColumns = ["id"],
                    childColumns = ["person_id"],
                    onDelete = ForeignKey.CASCADE)])
class TrackrEvent (

        @PrimaryKey
        val id: String,

        var person_id: String,

        var type: Int,

        val date: Int,

        val reminder_interval: Int,

        val repeat_strategy: Int,
)