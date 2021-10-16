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
     * @param firstName     The first name of the person the event is for.
     * @param lastName      The last name of the person the event is for.
     * @param eventType     The type of the event to be removed.
     * @param date          The date of the event to be created.
     * @param remindDate    The date to send a notification for the event.
     * @return              Whether the event outlined by info was successfully created or not.
     */
    boolean add(String firstName, String lastName, EventOutputData.EventTypes eventType,
                LocalDate date, LocalDate remindDate);

    /**
     * Returns whether the event outlined by info was successfully removed or not.
     *
     * @param firstName     The first name of the person the event is for.
     * @param lastName      The last name of the person the event is for.
     * @param eventType     The type of the event to be removed.
     * @return              Whether the event outlined by info was successfully removed or not.
     */
    boolean remove(String firstName, String lastName, EventOutputData.EventTypes eventType);

    /**
     * Returns information on the event being specified.
     *
     * @param firstName     The first name of the person the event is for.
     * @param lastName      The last name of the person the event is for.
     * @param eventType     The type of the event to be removed.
     * @return              The information of the event specified.
     */
    EventOutputData view(String firstName, String lastName, EventOutputData.EventTypes eventType);
}
