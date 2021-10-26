package input_output_interfaces

import usecases.EventOutputData.EventTypes
import usecases.EventOutputData
import java.time.LocalDate

/**
 * Interface to handle giving inputs to EventManager from a presenter/controller so that it
 * can perform the specified methods, as well as receiving information back from
 * EventManager.
 */
interface EventInOut {
    /**
     * Returns whether the event outlined by info was successfully created or not.
     *
     * @param eventType     The type of the event to be removed.
     * @param firstName     The first name of the person the event is for.
     * @param lastName      The last name of the person the event is for.
     * @param date          The date of the event to be created.
     * @param remindDate    The date to send a notification for the event.
     * @return              Whether the event outlined by info was successfully created or not.
     */
    fun add(
        eventType: EventTypes?, firstName: String?, lastName: String?,
        date: LocalDate?, remindDate: LocalDate?
    ): Boolean

    /**
     * Returns whether the event outlined by info was successfully removed or not.
     *
     * @param eventType     The type of the event to be removed.
     * @param firstName     The first name of the person the event is for.
     * @param lastName      The last name of the person the event is for.
     *
     * @return              Whether the event outlined by info was successfully removed or not.
     */
    fun remove(eventType: EventTypes?, firstName: String?, lastName: String?): Boolean

    /**
     * Returns information on the event being specified.
     *
     * @param eventType     The type of the event to be removed.
     * @param firstName     The first name of the person the event is for.
     * @param lastName      The last name of the person the event is for.
     * @return              The information of the event specified.
     */
    fun view(eventType: EventTypes?, firstName: String?, lastName: String?): EventOutputData?

    /**
     * Returns a set of information for each event in the database.
     *
     * @return  A set of EventOutputData. One for each event in the database.
     */
    val all: Set<Any?>?
}