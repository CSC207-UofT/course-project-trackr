package entities

import java.time.LocalDate

class Anniversary
/**
 * Create a new Anniversary event with a reminder time.
 * @param person The person this anniversary is associated with
 * @param date the date this event will take place
 * @param reminderDeadline when this event should be reminded to the user.
 */
    (person: Person?, date: LocalDate?, reminderDeadline: LocalDate?) : Event(person, date, reminderDeadline) {
    /**
     * Create a new Anniversary event without a reminder time.
     * @param person The person this anniversary is associated with
     * @param date the date this event will take place
     */
    constructor(person: Person?, date: LocalDate?) : this(person, date, null) {}
}