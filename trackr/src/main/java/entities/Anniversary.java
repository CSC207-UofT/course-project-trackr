package entities;

import java.util.Date;

public class Anniversary extends Event{
    Person person;

    /**
     * Create a new Anniversary event with a reminder time.
     * @param name the name of this event
     * @param date the date this event will take place
     * @param person The person this anniversary is associated with
     * @param reminderDeadline when this event should be reminded to the user.
     */
    public Anniversary(String name, Date date, Date reminderDeadline, Person person) {
        super(name, date, reminderDeadline);
    }

    /**
     * Create a new Anniversary event without a reminder time.
     * @param name the name of this event
     * @param date the date this event will take place
     * @param person The person this anniversary is associated with
     */
    public Anniversary(String name, Date date, Person person) {
        this(name, date, null, person);
    }
}
