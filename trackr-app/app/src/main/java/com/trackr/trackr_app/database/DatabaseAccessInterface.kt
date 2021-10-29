package database

import usecases.EventOutputData.EventTypes

/**
 * An interface which preforms database operations and allows other classes to
 * operate on the database through its methods.
 */
interface DatabaseAccessInterface {
    /**
     * Return a Set of all Events in the Database
     * @return a Set of all Events in the Database
     */
    fun getEventData(): Set<Event?>?

    /**
     * Add a new Event to the Database. Return true if the Database was successfully added.
     * @param event the Event to add to the Database
     * @return a boolean
     */
    fun addEvent(event: entities.Event?): Boolean

    /**
     * Remove an existing Event from the Database. Return a boolean representing
     * whether the Event was successfully removed.
     * @param event the Event to remove
     * @return the boolean indicating if the Event was removed or not.
     */
    fun removeEvent(event: entities.Event?): Boolean

    /**
     * Return a List of Events in the Database which have a Person with the
     * specified first and last name.
     * @param targetEventType the event type for the specified event
     * @param firstName the firstName of the Person which the Event is for
     * @param lastName the lastName of the Person for which the Event is for
     * @return A List of Events which have the specified properties
     */
    fun findEvent(
        targetEventType: EventTypes?,
        firstName: String?,
        lastName: String?
    ): entities.Event?
}