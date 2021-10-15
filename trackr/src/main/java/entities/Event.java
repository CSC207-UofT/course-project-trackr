package entities;

import java.time.LocalDate;

public abstract class Event {
    LocalDate date;
    LocalDate reminderDeadline;
    Person person;

    /**
     * Create a new event with a reminder time.
     * @param person the person associated with this event
     * @param date the date this event will take place
     * @param reminderDeadline when this event should be reminded to the user.
     */
    public Event(Person person, LocalDate date, LocalDate reminderDeadline) {
        this.date = date;
        this.reminderDeadline = reminderDeadline;
        this.person = person;
    }

    /**
     * Create an event without a reminder time.
     * @param person the person associated with this event
     * @param date the date this event will take place
     */
    public Event(Person person, LocalDate date) {
        this(person, date, null);
    }

    public Person getPerson() {
        return person;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getReminderDeadline() {
        return reminderDeadline;
    }
    /**
     * Check whether the input date matches a reminderDeadline of this object
     * @param date The date to test for a deadline
     * @return A boolean value if the input matches a reminder deadline
     */
    public boolean isReminderDeadline(LocalDate date) {
        return date.equals(this.reminderDeadline);
    }
}
