package com.trackr.trackr_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user-table")
class User (
        @PrimaryKey
        val id: String,

        var username: String,
        )