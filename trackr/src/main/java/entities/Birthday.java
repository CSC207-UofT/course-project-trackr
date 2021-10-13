package entities;

import java.util.Date;

public class Birthday extends Event{
    /**
     * Create a new Birthday event with a reminder time.
     * @param person The person this birthday is associated with
     * @param date the date this event will take place
     * @param reminderDeadline when this event should be reminded to the user.
     */
    public Birthday(Person person, Date date, Date reminderDeadline) {
        super(person, date, reminderDeadline);
    }

    /**
     * Create a new Birthday event with a reminder time.
     * @param person The person this birthday is associated with
     * @param date the date this event will take place
     */
    public Birthday(Person person, Date date) {
        this(person, date, null);
    }
}
