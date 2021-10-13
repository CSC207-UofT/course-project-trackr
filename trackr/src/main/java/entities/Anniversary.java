package entities;

import java.util.Date;

public class Anniversary extends Event{
    /**
     * Create a new Anniversary event with a reminder time.
     * @param person The person this anniversary is associated with
     * @param date the date this event will take place
     * @param reminderDeadline when this event should be reminded to the user.
     */
    public Anniversary(Person person, Date date, Date reminderDeadline) {
        super(person, date, reminderDeadline);
    }

    /**
     * Create a new Anniversary event without a reminder time.
     * @param person The person this anniversary is associated with
     * @param date the date this event will take place
     */
    public Anniversary(Person person, Date date) {
        this(person, date, null);
    }
}
