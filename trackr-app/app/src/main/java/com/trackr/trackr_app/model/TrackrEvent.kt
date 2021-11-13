package com.trackr.trackr_app.model

import androidx.room.*
import java.util.*

@Entity(tableName = "event-table",
        // Establish foreign key relation
        foreignKeys = [
            ForeignKey(entity = Person::class,
                    parentColumns = ["id"],
                    childColumns = ["person_id"],
                    onDelete = ForeignKey.CASCADE)])
class TrackrEvent (

        var person_id: String,

        var type: Int,

        val date: Long,

        val reminder_interval: Int,

        val repeat_strategy: Int,

        @PrimaryKey
        val id: String = UUID.randomUUID().toString(),
)