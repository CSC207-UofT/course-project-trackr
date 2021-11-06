package database

import database.DataAccess
import usecases.EventOutputData.EventTypes
import entities.Anniversary
import entities.Birthday

/**
 * An implementation for the DatabaseAccessInterface, which preforms Database operations.
 */
class DataAccess : DatabaseAccessInterface {
    /**
     * Return a List of all Events in the Database
     * @return a list of all Events in the Database
     */
    fun getEventData(): Set<Event>
        return database.getEventData()

    /**
     * Add a new Event to the Database
     * @param event the Event to add to the Database
     */
    fun addEvent(event: entities.Event?): Boolean {
        return database.addEvent(event)
    }

    /**
     * Remove an existing Event from the Database. Return a boolean representing
     * whether the Event was successfully removed.
     * @param event the Event to remove
     * @return the boolean indicating if the Event was removed or not.
     */
    fun removeEvent(event: entities.Event?): Boolean {
        return database.removeEvent(event)
    }

    /**
     * Return an Event in the Database which have the corresponding type and
     * specified first and last name.
     * @param targetEventType the EventType of the target Event
     * @param firstName the firstName of the Person which the Event is for
     * @param lastName the lastName of the Person for which the Event is for
     * @return the Event which has the specified properties, or null if none is found
     */
    fun findEvent(
        targetEventType: EventTypes,
        firstName: String?,
        lastName: String?
    ): entities.Event? {
        for (event in eventData) {
            val hasSameName = event.getPerson().getFirstName().equals(firstName) &&
                    event.getPerson().getLastName().equals(lastName)
            val hasSameType = event is Anniversary && targetEventType == EventTypes.ANNIVERSARY ||
                    event is Birthday && targetEventType == EventTypes.BIRTHDAY
            if (hasSameName && hasSameType) {
                return event
            }
        }
        return null
    }

    companion object {
        private val database: Database = Database()
    }
}