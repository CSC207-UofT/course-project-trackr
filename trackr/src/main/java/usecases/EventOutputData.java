package usecases;

import entities.Event;

import java.time.LocalDate;
import java.util.Set;

public class EventOutputData {
    private final String firstName;
    private final String lastName;
    private final String personDescription;
    private final Set<String> tags;
    private final LocalDate date;
    private final LocalDate remindDeadline;

    public EventOutputData(Event event) {
        this.firstName = event.getPerson().getFirstName();
        this.lastName = event.getPerson().getLastName();
        this.personDescription = event.getPerson().getDescription();
        this.tags = event.getPerson().getTags();
        this.date = event.getDate();
        this.remindDeadline = event.getReminderDeadline();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonDescription() {
        return personDescription;
    }

    public Set<String> getPersonTags() {
        return tags;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getRemindDeadline() {
        return remindDeadline;
    }
}

