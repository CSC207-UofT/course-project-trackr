package usecases

import database.DatabaseAccessInterface
import input_output_interfaces.EventInOut
import EventOutputData.EventTypes
import com.trackr.trackr_app.entities.Event
import entities.Person
import java.time.LocalDate
import java.util.HashSet

/**
 * EventManager is a use case class that manages the creation and deletion of events
 */
class EventManager(private val dataAccessor: DatabaseAccessInterface) : EventInOut {
    private val personManager: PersonManager
    val allEvents: Set<Event?>?
        get() = dataAccessor.eventData

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
        event = if (eventType.equals(EventOutputData.EventTypes.BIRTHDAY)) {
            Birthday(person, date, reminderDeadline)
        } else {
            Anniversary(person, date, reminderDeadline)
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
        val toRemove = dataAccessor.findEvent(eventType, firstName, lastName)
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
        val event = dataAccessor.findEvent(eventType, firstName, lastName)
        return EventOutputData(event)
    }

    override fun add(
        eventType: EventTypes, firstName: String?, lastName: String?,
        date: LocalDate?, remindDate: LocalDate?
    ): Boolean {
        val person: Person = personManager.createPerson(firstName, lastName)
        return addEvent(person, eventType, date, remindDate)
    }

    override fun remove(eventType: EventTypes?, firstName: String?, lastName: String?): Boolean {
        return removeEvent(eventType, firstName, lastName)
    }

    override fun view(
        eventType: EventTypes?,
        firstName: String?,
        lastName: String?
    ): EventOutputData {
        return viewEvent(eventType, firstName, lastName)
    }

    override val all: Set<Any>
        get() {
            val events = allEvents
            val set: MutableSet<EventOutputData> = HashSet<EventOutputData>()
            for (e in events!!) {
                set.add(EventOutputData(e))
            }
            return set
        }

    init {
        personManager = PersonManager()
    }
}