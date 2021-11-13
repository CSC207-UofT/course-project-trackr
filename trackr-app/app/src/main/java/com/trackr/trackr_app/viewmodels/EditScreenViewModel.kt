package com.trackr.trackr_app.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val personRepository: PersonRepository,
    state: SavedStateHandle
) : ViewModel() {

    private val eventID: String = state.get<String>("eventId")!!

    private val _eventName = mutableStateOf("Birthday")
    val eventName: State<String> get() = _eventName

    private var eventType: Int = 0

    private val _eventDate = mutableStateOf(LocalDate.of(2020, 1, 1))
    val eventDate: State<LocalDate> get() = _eventDate

    private val _chosenReminder = mutableStateOf("1 day before")
    val chosenReminder: State<String> get() = _chosenReminder

    private val _personName = mutableStateOf("")
    val personName: State<String> get() = _personName

    init {
        viewModelScope.launch {
            val event = eventRepository.getById(eventID)
            _eventName.value = if (event.type == 0) "Birthday" else "Anniversary"
            _eventDate.value = LocalDate.ofEpochDay(event.date)
            _chosenReminder.value = getReminderMap()
                .entries.associate { (s, i) -> i to s }[event.reminder_interval]!!

            val associatedPerson = personRepository.getPersonById(event.person_id)
            _personName.value = associatedPerson.first_name + " " + associatedPerson.last_name
        }
    }

    fun editEventName(newEventName: String) {
        _eventName.value = newEventName
        eventType = if (newEventName == "Birthday") 0 else 1
    }

    fun changeMonth(newMonth: String) {
        _eventDate.value = _eventDate.value
                .withMonth(getMonths().indexOf(newMonth) + 1)
                .withDayOfMonth(_eventDate.value.dayOfMonth)
    }

    fun changeDay(newDay: Int) {
        _eventDate.value = _eventDate.value.withDayOfMonth(newDay)
    }

    fun changeReminderInterval(newInterval: String) {
        _chosenReminder.value = newInterval
    }

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

    fun getReminderIntervals(): List<String> {
        return listOf(
            "1 day before",
            "3 days before",
            "1 week before",
            "2 weeks before",
            "1 month before"
        )
    }

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
     * Edits/deletes an event using EventRepository
     */
    fun editEvent() = viewModelScope.launch {
        val event = eventRepository.getById(eventID)
        val reminderInt: Int? = getReminderMap()[chosenReminder.value]

        eventRepository.editInterval(reminderInt ?: 1, event)
        eventRepository.editDate(eventDate.value.withYear(1970), event)

        eventRepository.editType(eventType, event)
    }

    /**
     * Delete the event from the database
     */
    fun deleteEvent() = viewModelScope.launch {
        val event = eventRepository.getById(eventID)
        eventRepository.delete(event)
    }
}

