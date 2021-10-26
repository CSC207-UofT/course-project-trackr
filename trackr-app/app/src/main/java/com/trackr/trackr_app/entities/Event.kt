package entities

import java.time.LocalDate
import kotlin.jvm.JvmOverloads

abstract class Event @JvmOverloads constructor(
    person: Person?,
    val date: LocalDate?,
    val reminderDeadline: LocalDate? = null
) {
    val person: entities.Person
    fun getPerson(): entities.Person {
        return person
    }

    /**
     * Check whether the input date matches a reminderDeadline of this object
     * @param date The date to test for a deadline
     * @return A boolean value if the input matches a reminder deadline
     */
    fun isReminderDeadline(date: LocalDate): Boolean {
        return date == reminderDeadline
    }
    /**
     * Create a new event with a reminder time.
     * @param person the person associated with this event
     * @param date the date this event will take place
     * @param reminderDeadline when this event should be reminded to the user.
     */
    /**
     * Create an event without a reminder time.
     * @param person the person associated with this event
     * @param date the date this event will take place
     */
    init {
        this.person = person
    }
}