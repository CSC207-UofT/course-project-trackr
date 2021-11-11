package com.trackr.trackr_app.model

import androidx.room.*
import java.sql.Date
import java.time.LocalDate

@Entity(tableName = "event-table",
        // Establish foreign key relation
        foreignKeys = [
            ForeignKey(entity = Person::class,
                    parentColumns = ["id"],
                    childColumns = ["person_id"],
                    onDelete = ForeignKey.CASCADE)])
class Event(

        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String,

        @ColumnInfo(name = "person_id")
        var person_id: String,

        @ColumnInfo(name = "type")
        var type: Int,

        @ColumnInfo(name = "date")
        val date: Date,

        @ColumnInfo(name = "reminder_interval")
        val reminder_interval: Int,

        @ColumnInfo(name = "repeat_strategy")
        val repeat_strategy: Int,
) {

    /**
     * Check whether the input date matches a reminderDeadline of this object
     * @param date The date to test for a deadline
     * @return A boolean value if the input matches a reminder deadline
     */
    fun isReminderDeadline(date: LocalDate): Boolean {
        return date == date.minusDays(reminder_interval.toLong())
    }
}