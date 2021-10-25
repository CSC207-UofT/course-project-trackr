package usecases;

import entities.Anniversary;
import entities.Event;

import java.time.LocalDate;
import java.util.Set;

/**
 * A dataclass representing the Data that Presenter will retrieve from EventManager
 */
public class EventOutputData {
    private EventTypes eventType;
    private String firstName;
    private String lastName;
    private String personDescription;
    private Set<String> tags;
    private LocalDate date;
    private LocalDate remindDeadline;

    public enum EventTypes {
        ANNIVERSARY,
        BIRTHDAY,
    }

    public EventOutputData(Event event) {
        if (event != null) {
            this.eventType = event instanceof Anniversary ? EventTypes.ANNIVERSARY : EventTypes.BIRTHDAY;
            this.firstName = event.getPerson().getFirstName();
            this.lastName = event.getPerson().getLastName();
            this.personDescription = event.getPerson().getDescription();
            this.tags = event.getPerson().getTags();
            this.date = event.getDate();
            this.remindDeadline = event.getReminderDeadline();
        }
    }

    public String getEventType() {
        return eventType.toString();
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

