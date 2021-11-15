package com.trackr.trackr_app.viewmodels

import android.os.UserManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.trackr.trackr_app.manager.EventManager
import com.trackr.trackr_app.manager.PersonManager
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.model.User
import com.trackr.trackr_app.notification.EventNotificationManager
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.UUID.randomUUID
import javax.inject.Inject

/**
 * The view model for the AddScreen which manages the data that appears on the AddScreen page
 * @param eventRepository an instance of the EventRepository class that can be used to store new
 * events in the data base
 * @param personRepository an instance of the PersonRepository class that can be used store new
 * persons in the data base
 * @param userRepository an instance of the UserRepository class that can be used store new
 * users in the data base
 * @param eventNotificationManager used to set a notification upon event creation
 */
@HiltViewModel
class AddScreenViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val personRepository: PersonRepository,
    private val userRepository: UserRepository,
    private val eventNotificationManager: EventNotificationManager,
    private val eventManager: EventManager,
    private val personManager: PersonManager,
): ViewModel() {

    //Define variables for the input fields
    private val _firstName = mutableStateOf("")
    val firstName: State<String> get() = _firstName

    private val _lastName = mutableStateOf("")
    val lastName: State<String> get() = _lastName

    private val _eventName = mutableStateOf("Birthday")
    val eventName: State<String> get() = _eventName

    private var eventType: Int = 0

    private val _eventDate = mutableStateOf(LocalDate.now())
    val eventDate: State<LocalDate> get() = _eventDate

    private val _chosenReminder = mutableStateOf("1 day before")
    val chosenReminder: State<String> get() = _chosenReminder

    /**
     * Edit the first name of the person the added event corresponds to
     * @param newFirstName the value given by the first name text input field
     */
    fun editFirstName(newFirstName: String) {
        _firstName.value = newFirstName
    }

    /**
     * Edit the last name of the person the added event corresponds to
     * @param newLastName the value given by the last name text input field
     */
    fun editLastName(newLastName: String) {
        _lastName.value = newLastName
    }

    /**
     * Edit the name/type of the event (either Birthday or Anniversary)
     * @param newEventName the value given by the radio buttons
     */
    fun editEventName(newEventName: String) {
        _eventName.value = newEventName
        eventType = if (newEventName == "Birthday") 0 else 1
    }

    /**
     * Changes the month of the added event
     * @param newMonth the value given by the month dropdown
     */
    fun changeMonth(newMonth: String) {
        _eventDate.value = _eventDate.value
            .withMonth(getMonths().indexOf(newMonth) + 1)
            .withDayOfMonth(_eventDate.value.dayOfMonth)
    }

    /**
     * Changes the day of the added event
     * @param newDay the value given by the day dropdown
     */
    fun changeDay(newDay: Int) {
        _eventDate.value = _eventDate.value.withDayOfMonth(newDay)
    }

    /**
     * Changes the year of the added event
     * @param newYear the value given by the year dropdown
     */
    fun changeYear(newYear: Int) {
        _eventDate.value = _eventDate.value.withYear(newYear)
    }

    /**
     * Changes the the time frame in which to remind the user of the added event
     * @param newInterval the value given by the reminder interval dropdown
     */
    fun changeReminderInterval(newInterval: String) {
        _chosenReminder.value = newInterval
    }

    /**
     * Return a list of all months for the month dropdown
     */
    fun getMonths(): List<String> {
        return listOf(
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec"
        )
    }

    /**
     * Return a list of all reminder intervals for the reminder dropdown
     */
    fun getReminderIntervals(): List<String> {
        return listOf(
            "1 day before", "3 days before",
            "1 week before", "2 weeks before", "1 month before"
        )
    }

    /**
     * Aggregate the data that has been inputted and then tell the user, person, and event
     * repositories to store this data
     */
    fun addEvent() = viewModelScope.launch {
        //Add the current user to the database
        val defaultUser = User("Default User")
        userRepository.insert(defaultUser)

        //Add the person specified by the first and last name fields to the database
        val newPerson = personManager.createPerson(defaultUser.id, firstName.value, lastName.value)
        personRepository.insert(newPerson)

        //Convert the reminder interval to an int using the following mapping
        val reminderInt: Int = mapOf(
            "1 day before" to 1,
            "3 days before" to 3,
            "1 week before" to 7,
            "2 weeks before" to 14,
            "1 month before" to 30
        )[chosenReminder.value]!!

        //Add the new event to the database
        val newEvent = eventManager.createEvent(
                            newPerson.id,
                            eventType,
                            eventDate.value.withYear(2008)
                                .toEpochDay(),
                            eventDate.value.year,
                            reminderInt, 0)
        eventRepository.insert(newEvent)

        //Add notification
        eventNotificationManager.createNotification(
                "${firstName.value} ${lastName.value}",
                eventName.value,
                eventDate.value,
                eventDate.value.minusDays(reminderInt.toLong()),
                newEvent.id.hashCode()
        )
    }
}
