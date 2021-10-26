package usecases

import database.DatabaseAccessInterface

/**
 * EventManager is a use case class that manages the creation and deletion of events
 */
class EventManager(dataAccessorObj: DatabaseAccessInterface) : EventInOut {
    private val dataAccessor: DatabaseAccessInterface
    private val personManager: PersonManager
    val allEvents: Set<Any>
        get() = dataAccessor.getEventData()

    /**
     * Add an event to the database
     * @param person - the key we are adding to the hashmap
     * @param eventType - a string representing the type of event (either Birthday or Anniversary)
     * @param date - the date of the event
     * @param reminderDeadline - the date to innitiate a reminder
     * @return true if the event was successfully added
     */
    fun addEvent(
        person: Person?, eventType: EventTypes,
        date: LocalDate?, reminderDeadline: LocalDate?
    ): Boolean {
        val event: Event
        if (eventType.equals(EventOutputData.EventTypes.BIRTHDAY)) {
            event = Birthday(person, date, reminderDeadline)
        } else {
            event = Anniversary(person, date, reminderDeadline)
        }
        return dataAccessor.addEvent(event)
    }

    /**
     * Remove an event from the database.
     * @param eventType - The type of the event to be removed.
     * @param firstName - the first name of the person whose events we are removing
     * @param lastName - the last name of the person whose events we are removing
     * @return false if no events were removed, and true if one or more events were removed
     */
    fun removeEvent(eventType: EventTypes?, firstName: String?, lastName: String?): Boolean {
        val toRemove: Event = dataAccessor.findEvent(eventType, firstName, lastName)
        return dataAccessor.removeEvent(toRemove)
    }

    /**
     * Returns information on the event being specified.
     *
     * @param eventType     The type of the event to be removed.
     * @param firstName     The first name of the person the event is for.
     * @param lastName      The last name of the person the event is for.
     * @return              The information of the event specified.
     */
    fun viewEvent(eventType: EventTypes?, firstName: String?, lastName: String?): EventOutputData {
        val event: Event = dataAccessor.findEvent(eventType, firstName, lastName)
        return EventOutputData(event)
    }

    @Override
    fun add(
        eventType: EventTypes, firstName: String?, lastName: String?,
        date: LocalDate?, remindDate: LocalDate?
    ): Boolean {
        val person: Person = personManager.createPerson(firstName, lastName)
        return addEvent(person, eventType, date, remindDate)
    }

    @Override
    fun remove(eventType: EventTypes?, firstName: String?, lastName: String?): Boolean {
        return removeEvent(eventType, firstName, lastName)
    }

    @Override
    fun view(eventType: EventTypes?, firstName: String?, lastName: String?): EventOutputData {
        return viewEvent(eventType, firstName, lastName)
    }

    @get:Override
    val all: Set<Any>
        get() {
            val events: Set<Event> = allEvents
            val set: Set<EventOutputData> = HashSet()
            for (e in events) {
                set.add(EventOutputData(e))
            }
            return set
        }

    init {
        dataAccessor = dataAccessorObj
        personManager = PersonManager()
    }
}