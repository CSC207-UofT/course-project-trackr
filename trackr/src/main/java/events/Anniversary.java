package events;

import java.util.Date;

public class Anniversary extends Event{
    Person person;

    public Anniversary(String name, Date date, Date reminderDeadline, Person person) {
        super(name, date, reminderDeadline);
    }

    public Anniversary(String name, Date date, Person person) {
        this(name, date, null, person);
    }
}
