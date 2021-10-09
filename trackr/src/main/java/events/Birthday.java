package events;

import java.util.Date;

public class Birthday extends Event{
    Person person;

    public Birthday(String name, Date date, Date reminderDeadline, Person person) {
        super(name, date, reminderDeadline);
    }

    public Birthday(String name, Date date, Person person) {
        this(name, date, null, person);
    }
}
