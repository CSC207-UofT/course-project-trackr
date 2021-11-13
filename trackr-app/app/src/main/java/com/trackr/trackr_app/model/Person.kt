package com.trackr.trackr_app.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.util.*

/**
 * A Person who is the target for an Event.
 * The Person class is responsible for storing the first and last name of the person,
 * along with a description and tags associated with this person.
 */
@Entity(tableName = "person-table",
        // Establish foreign key relation
        foreignKeys = [
                ForeignKey(entity = User::class,
                        parentColumns = ["id"],
                        childColumns = ["user_id"],
                        onDelete = CASCADE)])
class Person(
        val user_id: String,

        var first_name: String,

        var last_name: String = "",

        @PrimaryKey
        val id: String = UUID.randomUUID().toString(),
//        private val tags: HashSet<String> = HashSet()
)