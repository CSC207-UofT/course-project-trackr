package com.trackr.trackr_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user-table")
class User (
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: UUID,

        @ColumnInfo(name = "username")
        var username: String,
        )