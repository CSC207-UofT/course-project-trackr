package com.trackr.trackr_app.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trackr.trackr_app.manager.EventCreator
import com.trackr.trackr_app.manager.SinglePersonAccessor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

/**
 * The view model for the AddScreen which manages the data that appears on the AddScreen page
 * @param singlePersonAccessor an object that implements SinglePersonAccessor and is used to access
 * a single person from the database.
 * @param eventCreator an instance of a class which implements EventCreator that is used to create
 * new events and add them to the database
 */
@HiltViewModel
class AddScreenViewModel @Inject constructor(
    private val singlePersonAccessor: SinglePersonAccessor,
    private val eventCreator: EventCreator,
    state: SavedStateHandle
) : ViewModel() {

    private val personID: String = state.get<String>("personId")!!

    private val _firstName = mutableStateOf("")
    val firstName: State<String> get() = _firstName

    private val _lastName = mutableStateOf("")
    val lastName: State<String> get() = _lastName

    //Define variables for the input fields
    private val _eventName = mutableStateOf("Birthday")
    val eventName: State<String> get() = _eventName

    private var eventType: Int = 0

    private val _eventDate = mutableStateOf(LocalDate.now())
    val eventDate: State<LocalDate> get() = _eventDate

    private val _chosenReminder = mutableStateOf("1 day before")
    val chosenReminder: State<String> get() = _chosenReminder

    init {
        viewModelScope.launch {
            val person = singlePersonAccessor.getPersonById(personID)
            _firstName.value = person.firstName
            _lastName.value = person.lastName
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
            "1 day before", "3 days before",
            "1 week before", "2 weeks before", "1 month before"
        )
    }

    /**
     * Create a new event based off the current event data and add it to the database
     */
    fun addEvent() = viewModelScope.launch {
        //Add the current user to the database
        eventCreator.addEvent(
            personID,
            eventType,
            chosenReminder.value,
            eventDate.value
        )
    }
}