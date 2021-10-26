package entities

import java.time.LocalDate
import kotlin.jvm.JvmOverloads

class Birthday
/**
 * Create a new Birthday event with a reminder time.
 * @param person The person this birthday is associated with
 * @param date the date this event will take place
 * @param reminderDeadline when this event should be reminded to the user.
 */
/**
 * Create a new Birthday event with a reminder time.
 * @param person The person this birthday is associated with
 * @param date the date this event will take place
 */
@JvmOverloads constructor(
    person: entities.Person?,
    date: LocalDate?,
    reminderDeadline: LocalDate? = null
) : entities.Event(person, date, reminderDeadline)