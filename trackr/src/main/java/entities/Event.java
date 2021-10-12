package entities;

import java.util.Date;

public abstract class Event {
    String name;
    Date date;
    Date reminderDeadline;

    /**
     * Create a new event with a reminder time.
     * @param name the name of this event
     * @param date the date this event will take place
     * @param reminderDeadline when this event should be reminded to the user.
     */
    public Event(String name, Date date, Date reminderDeadline) {
        this.name = name;
        this.date = date;
        this.reminderDeadline = reminderDeadline;
    }

    /**
     * Create an event without a reminder time.
     * @param name the name of this event
     * @param date the date this event will take place
     */
    public Event(String name, Date date) {
        this(name, date, null);
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    /**
     * Check whether the input date matches a reminderDeadline of this object
     * @param date The date to test for a deadline
     * @return A boolean value if the input matches a reminder deadline
     */
    public boolean isReminderDeadline(Date date) {
        return date.equals(this.reminderDeadline);
    }
}
