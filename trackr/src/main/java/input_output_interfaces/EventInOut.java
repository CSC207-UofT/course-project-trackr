package input_output_interfaces;

import usecases.EventOutputData;

import java.time.LocalDate;

/**
 * Interface to handle giving inputs to EventManager from a presenter/controller so that it
 * can perform the specified methods, as well as receiving information back from
 * EventManager.
 */
public interface EventInOut {
    /**
     * Returns whether the event outlined by info was successfully created or not.
     *
     * @param name          The first and last name in the form [<firstname>,<lastname>] of the person
     *                      the event is for.
     * @param date          The date of the event to be created.
     * @param remindDate    The date to send a notification for the event.
     * @return              Whether the event outlined by info was successfully created or not.
     */
    boolean addEvent(String[] name, LocalDate date, LocalDate remindDate);

    /**
     * Returns whether the event outlined by info was successfully removed or not.
     *
     * @param name  The first and last name in the form [<firstname>,<lastname>] of the person
     *              the event is for.
     * @param date  The date of the event to be removed.
     * @return      Whether the event outlined by info was successfully removed or not.
     */
    boolean removeEvent(String[] name, LocalDate date);

    /**
     * Returns information on the event being specified.
     *
     * @param name  The first and last name in the form [<firstname>,<lastname>] of the person
     *              the event is for.
     * @return      The information of the event specified.
     */
    EventOutputData getEventInfo(String[] name);



}
