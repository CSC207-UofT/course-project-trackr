package com.trackr.trackr_app.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*

/**
 * A Person who is the target for an Event.
 * The Person class is responsible for storing the first and last name of the person,
 * along with a description and tags associated with this person.
 */
@Entity(
    tableName = "person-table",
    // Establish foreign key relation
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = CASCADE
        )]
)
class Person(
    val userId: String,

    var firstName: String,

    var lastName: String = "",

    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
//        private val tags: HashSet<String> = HashSet()
)