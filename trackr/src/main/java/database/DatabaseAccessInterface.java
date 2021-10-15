package database;

import entities.Event;
import usecases.EventOutputData.EventTypes;

import java.util.Set;

/**
 * An interface which preforms database operations and allows other classes to
 * operate on the database through its methods.
 */
public interface DatabaseAccessInterface {
    /**
     * Return a List of all Events in the Database
     * @return a list of all Events in the Database
     */
    Set<Event> getEventData();

    /**
     * Add a new Event to the Database
     * @param event the Event to add to the Database
     */
    void addEvent(Event event);

    /**
     * Remove an existing Event from the Database. Return a boolean representing
     * whether the Event was successfully removed.
     * @param event the Event to remove
     * @return the boolean indicating if the Event was removed or not.
     */
    boolean removeEvent(Event event);

    /**
     * Return a List of Events in the Database which have a Person with the
     * specified first and last name.
     * @param firstName the firstName of the Person which the Event is for
     * @param lastName the lastName of the Person for which the Event is for
     * @return A List of Events which have the specified properties
     */
    Event findEvent(EventTypes targetEventType, String firstName, String lastName);
}
