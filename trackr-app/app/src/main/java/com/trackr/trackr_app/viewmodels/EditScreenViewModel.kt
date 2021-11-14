package com.trackr.trackr_app.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.trackr.trackr_app.notification.EventNotificationManager
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

/**
 * The view model for the EditScreen which manages the data that appears on the EditScreen page
 * @param eventRepository an instance of the EventRepository class that can be used to store new
 * events in the data base
 * @param personRepository an instance of the PersonRepository class that can be used store new
 * persons in the data base
 * @param eventNotificationManager used to set a notification upon event creation
 */
@HiltViewModel
class EditScreenViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val personRepository: PersonRepository,
    state: SavedStateHandle,
    private val eventNotificationManager: EventNotificationManager

) : ViewModel() {

    private val eventID: String = state.get<String>("eventId")!!

    //Define variables for the input fields
    private val _eventName = mutableStateOf("Birthday")
    val eventName: State<String> get() = _eventName

    private var eventType: Int = 0

    private val _eventDate = mutableStateOf(LocalDate.of(2020, 1, 1))
    val eventDate: State<LocalDate> get() = _eventDate

    private val _chosenReminder = mutableStateOf("1 day before")
    val chosenReminder: State<String> get() = _chosenReminder

    private val _personName = mutableStateOf("")
    val personName: State<String> get() = _personName

    //Get the data from the database for the event we are editing
    init {
        viewModelScope.launch {
            val event = eventRepository.getById(eventID)
            _eventName.value = if (event.type == 0) "Birthday" else "Anniversary"
            _eventDate.value = LocalDate.ofEpochDay(event.date).withYear(event.firstYear)
            _chosenReminder.value = getReminderMap()
                .entries.associate { (s, i) -> i to s }[event.reminder_interval]!!

            val associatedPerson = personRepository.getPersonById(event.person_id)
            _personName.value = associatedPerson.first_name + " " + associatedPerson.last_name
        }
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
            "1 day before",
            "3 days before",
            "1 week before",
            "2 weeks before",
            "1 month before"
        )
    }

    //Convert the reminder interval to an int using the following mapping
    private fun getReminderMap(): Map<String, Int> {
        return mapOf(
                "1 day before" to 1,
                "3 days before" to 3,
                "1 week before" to 7,
                "2 weeks before" to 14,
                "1 month before" to 30
        )
    }


    /**
     * Aggregate the data that has been inputted and then tell the user, person, and event
     * repositories to update this data in the database
     */
    fun editEvent() = viewModelScope.launch {
        val event = eventRepository.getById(eventID)
        val reminderInt: Int = getReminderMap()[chosenReminder.value]!!
      
        eventRepository.editInterval(reminderInt, event)
        
        eventRepository.editDate(eventDate.value.withYear(1970), event)

        eventRepository.editFirstYear(eventDate.value.year, event)
        eventRepository.editType(eventType, event)

        //Edit notification
        eventNotificationManager.editNotification(
                personName.value,
                eventName.value,
                eventDate.value,
                eventDate.value.minusDays(reminderInt.toLong()),
                event.id.hashCode()
        )
    }

    /**
     * Delete the event from the database
     */
    fun deleteEvent() = viewModelScope.launch {
        val event = eventRepository.getById(eventID)
        eventRepository.delete(event)

        //Delete notification
        eventNotificationManager.removeNotification(event.id.hashCode())
    }
}

