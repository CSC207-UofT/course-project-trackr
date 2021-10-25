package entities;

import java.time.LocalDate;

public class Birthday extends Event{
    /**
     * Create a new Birthday event with a reminder time.
     * @param person The person this birthday is associated with
     * @param date the date this event will take place
     * @param reminderDeadline when this event should be reminded to the user.
     */
    public Birthday(Person person, LocalDate date, LocalDate reminderDeadline) {
        super(person, date, reminderDeadline);
    }

    /**
     * Create a new Birthday event with a reminder time.
     * @param person The person this birthday is associated with
     * @param date the date this event will take place
     */
    public Birthday(Person person, LocalDate date) {
        this(person, date, null);
    }
}
