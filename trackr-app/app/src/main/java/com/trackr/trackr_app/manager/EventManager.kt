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
) {
    fun createEvent(id: String, type: Int, date: Long, firstYear: Int, reminderInterval: Int,
                    reminderStrategy: Int): TrackrEvent {
        return TrackrEvent(id, type, date, firstYear, reminderInterval, reminderStrategy)
    }

    suspend fun addEvent(firstName: String, lastName: String, eventType: Int, chosenReminder: String,
                         eventDate: LocalDate) {
        //Add the person specified by the first and last name fields to the database
        val newPerson = personManager.materializePerson(firstName, lastName)

        //Convert the reminder interval to an int using the following mapping
        val reminderInt: Int = mapOf(
            "1 day before" to 1,
            "3 days before" to 3,
            "1 week before" to 7,
            "2 weeks before" to 14,
            "1 month before" to 30
        )[chosenReminder]!!

        //Add the new event to the database
        val newEvent = createEvent(
            newPerson.id,
            eventType,
            eventDate.withYear(2008)
                .toEpochDay(),
            eventDate.year,
            reminderInt, 0)
        eventRepository.insert(newEvent)

        //Add notification
        eventNotificationManager.createNotification(
            "$firstName $lastName",
            if (eventType == 0) "Birthday" else "Anniversary",
            eventDate,
            eventDate.minusDays(reminderInt.toLong()),
            newEvent.id
        )
    }

    /**
     * Aggregate the data that has been inputted and then tell the user, person, and event
     * repositories to update this data in the database
     */
    suspend fun editEvent(eventID: String, reminderInt: Int, eventDate: LocalDate, eventType: Int,
                          personName: String, eventName: String) {
        val event = eventRepository.getById(eventID)

        eventRepository.editInterval(reminderInt, event)

        eventRepository.editDate(eventDate.withYear(2008), event)

        eventRepository.editFirstYear(eventDate.year, event)
        eventRepository.editType(eventType, event)

        //Edit notification
        eventNotificationManager.editNotification(
            personName,
            eventName,
            eventDate,
            eventDate.minusDays(reminderInt.toLong()),
            event.id
        )
    }

    /**
     * Delete the event from the database
     */
    suspend fun deleteEvent(eventID: String) {
        val event = eventRepository.getById(eventID)
        eventRepository.delete(event)

        //Delete notification
        eventNotificationManager.removeNotification(event.id)
    }

    /**
     * Get event info from the database
     */
    suspend fun getEventInfo(eventID: String): List<Any> {
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