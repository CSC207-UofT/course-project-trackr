package interface_adapters;

import entities.Event;
import entities.Person;

import java.time.LocalDate;
import java.util.Set;

public interface EventInputBoundary {

    boolean addEvent(String firstName, String lastName, String eventType, LocalDate date, LocalDate reminderDeadline);

    boolean removeEvent(String eventType, String firstName, String lastName);

}
