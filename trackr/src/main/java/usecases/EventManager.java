package usecases;

import database.DatabaseAccessInterface;
import entities.Anniversary;
import entities.Birthday;
import entities.Event;
import entities.Person;
import interface_adapters.EventInputBoundary;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * EventManager is a use case class that manages the creation and deletion of events
 */
public class EventManager implements EventInputBoundary {

    private PersonManager personManager = new PersonManager();
    private DatabaseAccessInterface dataAccessor;

    public EventManager(DatabaseAccessInterface dataAccessorObj) {
        this.dataAccessor = dataAccessorObj;
    }

    public Set<Event> getAllEvents() {
        return dataAccessor.getEventData();
    }

    /**
     * Add an event to the database
     * @param firstName - first name of the person for which the event corresponds
     * @param lastName - last name of the person for which the event corresponds
     * @param eventType - a string representing the type of event (either Birthday or Anniversary)
     * @param date - the date of the event
     * @param reminderDeadline - the date to innitiate a reminder
     * @return true if the event was successfully added
     */
    public boolean addEvent(String firstName, String lastName, String eventType, LocalDate date, LocalDate reminderDeadline) {
        Person personObj = personManager.createPerson(firstName, lastName);
        Event event;
        if (eventType.equals("Birthday")) {
            event = new Birthday(personObj, date, reminderDeadline);
        } else {
            event = new Anniversary(personObj, date, reminderDeadline);
        }
        dataAccessor.addEvent(event);
        return true;
    }

    /**
     * Remove an event from the event hash map
     * @param eventType - either Birthday or Anniversary
     * @param firstName - the first name of the person whose events we are removing
     * @param lastName - the last name of the person whose events we are removing
     * @return false if no events were removed, and true if one or more events were removed
     */
    public boolean removeEvent(String eventType, String firstName, String lastName) {
        Event whichEventToRemove = dataAccessor.findEvent(eventType.equals("Birthday") ?
                EventOutputData.EventTypes.BIRTHDAY : EventOutputData.EventTypes.ANNIVERSARY, firstName, lastName);
        boolean eventRemoved = false;
        if (whichEventToRemove != null) {
            dataAccessor.removeEvent(whichEventToRemove);
            eventRemoved = true;
        }
        return eventRemoved;
    }
}
