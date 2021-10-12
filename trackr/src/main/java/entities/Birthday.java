package entities;

import java.util.Date;

public class Birthday extends Event{
    Person person;

    /**
     * Create a new Birthday event with a reminder time.
     * @param name the name of this event
     * @param date the date this event will take place
     * @param person The person this birthday is associated with
     * @param reminderDeadline when this event should be reminded to the user.
     */
    public Birthday(String name, Date date, Date reminderDeadline, Person person) {
        super(name, date, reminderDeadline);
    }

    /**
     * Create a new Birthday event with a reminder time.
     * @param name the name of this event
     * @param date the date this event will take place
     * @param person The person this birthday is associated with
     */
    public Birthday(String name, Date date, Person person) {
        this(name, date, null, person);
    }
}
