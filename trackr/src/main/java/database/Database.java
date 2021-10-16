package database;

import entities.Event;

import java.util.HashSet;
import java.util.Set;

public class Database {
    private final Set<Event> EventData = new HashSet<>();

    /**
     * Add a new Event to this Database. Return a boolean representing if the event was successfully added or not.
     * @param event the Event to add to this Database
     * @return a boolean representing if the event was successfully added or not.
     */
    public boolean addEvent(Event event) {
        return this.EventData.add(event);
    }

    /**
     * Remove an Event from this Database. Return boolean representing if the operation was successful.
     * @param event the Event to remove from the Database
     * @return a boolean representing if the Event was successfully removed from this Database
     */
    public boolean removeEvent(Event event) {
        return this.EventData.remove(event);
    }

    /**
     * Return a List of ALL Events in this Database
     * @return a List of ALL events in this Database
     */
    public Set<Event> getEventData() {
        return this.EventData;
    }
}
