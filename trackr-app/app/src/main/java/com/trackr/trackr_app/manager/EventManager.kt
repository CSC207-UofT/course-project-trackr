package com.trackr.trackr_app.manager

import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.notification.EventNotificationManager
import com.trackr.trackr_app.repository.EventRepository
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton


/**
 * The view model for the AddScreen which manages the data that appears on the AddScreen page
 *
 * @param eventRepository an instance of the EventRepository class that can be used to store new
 * events in the data base
 * @param eventNotificationManager used to set a notification upon event creation
 * @param personManager used to create a new Person object
 */
@Singleton
class EventManager @Inject constructor(
    private val eventRepository: EventRepository,
    private val eventNotificationManager: EventNotificationManager,
    private val personManager: PersonManager,
) : EventCreator, EventModifier, SingleEventAccessor {
    private fun createEvent(
        id: String, type: Int, date: Long, firstYear: Int, reminderInterval: Int
    ): TrackrEvent {
        return TrackrEvent(id, type, date, firstYear, reminderInterval)
    }

    override suspend fun addEvent(
        personId: String, eventType: Int, chosenReminder: Int,
        eventDate: LocalDate
    ) {
        // get the specified person for which this event is for
        val newPerson = personManager.getPersonById(personId)

        //Add the new event to the database
        val newEvent = createEvent(
            personId,
            eventType,
            eventDate.withYear(2008)
                .toEpochDay(),
            eventDate.year,
            chosenReminder
        )
        eventRepository.insert(newEvent)

        //Add notification
        eventNotificationManager.createNotification(
            "${newPerson.firstName} ${newPerson.lastName}",
            if (eventType == 0) "Birthday" else "Anniversary",
            eventDate,
            eventDate.minusDays(chosenReminder.toLong()),
            newEvent.id
        )
    }

    /**
     * Aggregate the data that has been inputted and then tell the user, person, and event
     * repositories to update this data in the database
     */
    override suspend fun editEvent(
        eventID: String, reminderInt: Int?, eventDate: LocalDate?, eventType: Int?
    ) {
        val event = eventRepository.getById(eventID)

        if (reminderInt != null) {
            eventRepository.editInterval(reminderInt, event)
        }

        if (eventDate != null) {
            eventRepository.editDate(eventDate.withYear(2008), event)
            eventRepository.editFirstYear(eventDate.year, event)
        }

        if (eventType != null) {
            eventRepository.editType(eventType, event)
        }

        //Edit notification
        eventNotificationManager.editNotification(
            personManager.getPersonById(event.personId).firstName,
            if (event.type == 0) "Birthday" else "Anniversary",
            LocalDate.ofEpochDay(event.date),
            LocalDate.ofEpochDay(event.date).minusDays(event.reminderInterval.toLong()),
            event.id
        )
    }

    /**
     * Delete the event from the database
     */
    override suspend fun deleteEvent(eventID: String) {
        val event = eventRepository.getById(eventID)
        eventRepository.delete(event)

        //Delete notification
        eventNotificationManager.removeNotification(event.id)
    }

    /**
     * Get event info from the database
     */
    override suspend fun getEventInfo(eventID: String): List<Any> {
        val event = eventRepository.getById(eventID)
        val associatedPerson = personManager.getPersonById(event.personId)

        return listOf<Any>(
            if (event.type == 0) "Birthday" else "Anniversary",
            event.date,
            event.firstYear,
            event.reminderInterval,
            associatedPerson.firstName + " " + associatedPerson.lastName,
            event.type
        )
    }
}