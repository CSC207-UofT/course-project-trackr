package database;

import entities.Anniversary;
import entities.Birthday;
import entities.Event;
import usecases.EventOutputData.EventTypes;

import java.util.Set;

/**
 * An implementation for the DatabaseAccessInterface, which preforms Database operations.
 */
public class DataAccess implements DatabaseAccessInterface{
    private final Database database = new Database();

    /**
     * Return a List of all Events in the Database
     * @return a list of all Events in the Database
     */
    @Override
    public Set<Event> getEventData() {
        return this.database.getEventData();
    }

    /**
     * Add a new Event to the Database
     * @param event the Event to add to the Database
     */
    @Override
    public void addEvent(Event event) {
        this.database.addEvent(event);
    }


    /**
     * Remove an existing Event from the Database. Return a boolean representing
     * whether the Event was successfully removed.
     * @param event the Event to remove
     * @return the boolean indicating if the Event was removed or not.
     */
    @Override
    public boolean removeEvent(Event event) {
        return this.database.removeEvent(event);
    }


    /**
     * Return an Event in the Database which have the corresponding type and
     * specified first and last name.
     * @param targetEventType the EventType of the target Event
     * @param firstName the firstName of the Person which the Event is for
     * @param lastName the lastName of the Person for which the Event is for
     * @return the Event which has the specified properties, or null if none is found
     */
    @Override
    public Event findEvent(EventTypes targetEventType, String firstName, String lastName) {
        for (Event event : this.getEventData()) {
           boolean hasSameName = event.getPerson().getFirstName().equals(firstName) &&
                                 event.getPerson().getLastName().equals(lastName);

           boolean hasSameType = (event instanceof Anniversary && targetEventType.equals(EventTypes.ANNIVERSARY)) ||
                   (event instanceof Birthday && targetEventType.equals(EventTypes.BIRTHDAY));

           if (hasSameName && hasSameType) {
               return event;
           }
        }
        return null;
    }
}