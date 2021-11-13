package com.trackr.trackr_app.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.model.User
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.sql.Date
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModel @Inject constructor(
    private val eventRepository: EventRepository,
) : ViewModel() {

    private val _delete = mutableStateOf("No")
    val delete: State<String> get() = _delete

    private val _eventName = mutableStateOf("Birthday")
    val eventName: State<String> get() = _eventName

    private var eventType: Int = 0

    private val _eventDate = mutableStateOf(LocalDate.of(2020, 1, 1))
    val eventDate: State<LocalDate> get() = _eventDate

    private val _chosenReminder = mutableStateOf("1 day before reminder")
    val chosenReminder: State<String> get() = _chosenReminder

    fun editDelete(newResult: String) {
        _delete.value = newResult
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
                "1 day before", "3 days before",
                "1 week before", "2 weeks before", "1 month before"
        )
    }
    /**
     * Edits/deletes an event using EventRepository
     *
     * @param data  list in the form [delete: String, new_month: String,
     *                                  new_date: String, new_interval: String, new_type: String]
     * @param id id of the event
     */
    fun editEvent(id: String) = viewModelScope.launch {
        val event = eventRepository.getById(id)

        if (delete.value == "Yes") {
            eventRepository.delete(event)
        } else {
            val reminderInt: Int? = mapOf("1 day before" to 1, "3 days before" to 3,
                    "1 week before" to 7, "2 weeks before" to 14,
                    "1 month before" to 30)[chosenReminder.value]
            eventRepository.editInterval(reminderInt ?: 1, event)

            eventRepository.editDate(eventDate.value.withYear(1970), event)

            eventRepository.editType(eventType, event)
        }
    }
}

