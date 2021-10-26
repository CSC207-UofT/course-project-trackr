package database

import java.util.HashSet

class Database {
    private val EventData: MutableSet<entities.Event> = HashSet<entities.Event>()

    /**
     * Add a new Event to this Database. Return a boolean representing if the event was successfully added or not.
     * @param event the Event to add to this Database
     * @return a boolean representing if the event was successfully added or not.
     */
    fun addEvent(event: entities.Event): Boolean {
        return EventData.add(event)
    }

    /**
     * Remove an Event from this Database. Return boolean representing if the operation was successful.
     * @param event the Event to remove from the Database
     * @return a boolean representing if the Event was successfully removed from this Database
     */
    fun removeEvent(event: entities.Event): Boolean {
        return EventData.remove(event)
    }

    /**
     * Return a List of ALL Events in this Database
     * @return a List of ALL events in this Database
     */
    fun getEventData(): Set<entities.Event> {
        return EventData
    }
}