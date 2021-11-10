package com.trackr.trackr_app.entities

import androidx.room.*
import java.time.LocalDate
import java.util.*

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
        val id: UUID,

        // Establish foreign key relation
        @Relation(
                parentColumn = "id",
                entityColumn = "person_id"
        )
        @ColumnInfo(name = "person_id")
        var person_id: UUID,

        @ColumnInfo(name = "type")
        var type: Int,

        @ColumnInfo(name = "date")
        val date: LocalDate,

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