package com.trackr.trackr_app.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "event-table",
    // Establish foreign key relation
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["id"],
            childColumns = ["personId"],
            onDelete = ForeignKey.CASCADE
        )]
)
class TrackrEvent(

    var personId: String,

    var type: Int,

    val date: Long,

    val firstYear: Int,

    val reminderInterval: Int,

    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
)