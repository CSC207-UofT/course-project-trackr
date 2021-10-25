package usecases;

import database.DatabaseAccessInterface;
import entities.Anniversary;
import entities.Birthday;
import entities.Event;
import entities.Person;
import input_output_interfaces.EventInOut;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * EventManager is a use case class that manages the creation and deletion of events
 */
public class EventManager implements EventInOut {

    private final DatabaseAccessInterface dataAccessor;
    private final PersonManager personManager;

    public EventManager(DatabaseAccessInterface dataAccessorObj) {
        this.dataAccessor = dataAccessorObj;
        this.personManager = new PersonManager();
    }

    public Set<Event> getAllEvents() {
        return dataAccessor.getEventData();
    }

    /**
     * Add an event to the database
     * @param person - the key we are adding to the hashmap
     * @param eventType - a string representing the type of event (either Birthday or Anniversary)
     * @param date - the date of the event
     * @param reminderDeadline - the date to innitiate a reminder
     * @return true if the event was successfully added
     */
    public boolean addEvent(Person person, EventOutputData.EventTypes eventType,
                            LocalDate date, LocalDate reminderDeadline) {
        Event event;
        if (eventType.equals(EventOutputData.EventTypes.BIRTHDAY)) {
            event = new Birthday(person, date, reminderDeadline);
        } else {
            event = new Anniversary(person, date, reminderDeadline);
        }

        return dataAccessor.addEvent(event);
    }

    /**
     * Remove an event from the database.
     * @param eventType - The type of the event to be removed.
     * @param firstName - the first name of the person whose events we are removing
     * @param lastName - the last name of the person whose events we are removing
     * @return false if no events were removed, and true if one or more events were removed
     */
    public boolean removeEvent(EventOutputData.EventTypes eventType, String firstName, String lastName) {
        Event toRemove = dataAccessor.findEvent(eventType, firstName, lastName);
        return dataAccessor.removeEvent(toRemove);
    }

    /**
     * Returns information on the event being specified.
     *
     * @param eventType     The type of the event to be removed.
     * @param firstName     The first name of the person the event is for.
     * @param lastName      The last name of the person the event is for.
     * @return              The information of the event specified.
     */
    public EventOutputData viewEvent(EventOutputData.EventTypes eventType, String firstName, String lastName) {
        Event event = dataAccessor.findEvent(eventType, firstName, lastName);
        return new EventOutputData(event);
    }

    @Override
    public boolean add(EventOutputData.EventTypes eventType, String firstName, String lastName,
                       LocalDate date, LocalDate remindDate) {
        Person person = personManager.createPerson(firstName, lastName);
        return this.addEvent(person, eventType, date, remindDate);
    }

    @Override
    public boolean remove(EventOutputData.EventTypes eventType, String firstName, String lastName) {
        return this.removeEvent(eventType, firstName, lastName);
    }

    @Override
    public EventOutputData view(EventOutputData.EventTypes eventType, String firstName, String lastName) {
        return this.viewEvent(eventType, firstName, lastName);
    }

    @Override
    public Set<EventOutputData> getAll() {
        Set<Event> events = this.getAllEvents();
        Set<EventOutputData> set = new HashSet<>();
        for (Event e : events) {
            set.add(new EventOutputData(e));
        }
        return set;
    }
}
