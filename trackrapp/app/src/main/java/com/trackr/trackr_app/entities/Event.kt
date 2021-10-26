package entities

import java.time.LocalDate

abstract class Event(person: Person?, date: LocalDate?, reminderDeadline: LocalDate?) {
    val date: LocalDate?
    val reminderDeadline: LocalDate?
    val person: Person?

    /**
     * Create an event without a reminder time.
     * @param person the person associated with this event
     * @param date the date this event will take place
     */
    constructor(person: Person?, date: LocalDate?) : this(person, date, null) {}

    fun getPerson(): Person? {
        return person
    }

    fun getDate(): LocalDate? {
        return date
    }

    fun getReminderDeadline(): LocalDate? {
        return reminderDeadline
    }

    /**
     * Check whether the input date matches a reminderDeadline of this object
     * @param date The date to test for a deadline
     * @return A boolean value if the input matches a reminder deadline
     */
    fun isReminderDeadline(date: LocalDate): Boolean {
        return date.equals(reminderDeadline)
    }

    /**
     * Create a new event with a reminder time.
     * @param person the person associated with this event
     * @param date the date this event will take place
     * @param reminderDeadline when this event should be reminded to the user.
     */
    init {
        this.date = date
        this.reminderDeadline = reminderDeadline
        this.person = person
    }
}