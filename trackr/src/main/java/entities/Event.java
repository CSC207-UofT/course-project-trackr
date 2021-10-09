package entities;

import java.util.Date;

public abstract class Event {
    String name;
    Date date;
    Date reminderDeadline;

    public Event(String name, Date date, Date reminderDeadline) {
        this.name = name;
        this.date = date;
        this.reminderDeadline = reminderDeadline;
    }

    public Event(String name, Date date) {
        this(name, date, null);
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public boolean isReminderDeadline(Date date) {
        return date.equals(this.reminderDeadline);
    }
}
