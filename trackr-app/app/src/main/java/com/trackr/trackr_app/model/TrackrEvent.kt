package com.trackr.trackr_app.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

/**
 * An Event for the program.
 *
 * The Event class is responsible for storing the details of a specific event
 * and know which person the event is for.
 */
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

    var date: Long,

    var firstYear: Int,

    var reminderInterval: Int,

    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
)