package com.trackr.trackr_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * A User for the program.
 *
 * A user should have a unique id and username.
 */
@Entity(tableName = "user-table")
class User(
    var username: String,

    @PrimaryKey
    val id: String = UUID.randomUUID().toString()
)