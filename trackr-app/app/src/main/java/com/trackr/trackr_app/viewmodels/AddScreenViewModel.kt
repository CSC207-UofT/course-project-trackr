package com.trackr.trackr_app.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.model.User
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.UUID.randomUUID
import javax.inject.Inject


@HiltViewModel
class AddScreenViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val personRepository: PersonRepository,
    private val userRepository: UserRepository
): ViewModel() {

    private val _firstName = mutableStateOf("")
    val firstName: State<String> get() = _firstName

    private val _lastName = mutableStateOf("")
    val lastName: State<String> get() = _lastName

    private val _eventName = mutableStateOf("Birthday")
    val eventName: State<String> get() = _eventName

    private var eventType: Int = 0

    private val _eventDate = mutableStateOf(LocalDate.of(1970, 1, 1))
    val eventDate: State<LocalDate> get() = _eventDate

    private val _chosenReminder = mutableStateOf("1 day before reminder")
    val chosenReminder: State<String> get() = _chosenReminder

    fun editFirstName(newFirstName: String) {
        _firstName.value = newFirstName
    }

    fun editLastName(newLastName: String) {
        _lastName.value = newLastName
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

    fun addEvent() = viewModelScope.launch {
        val defaultUser = User("Default User")
        userRepository.insert(defaultUser)

        val newPerson = Person(
            user_id = defaultUser.id,
            first_name = firstName.value,
            last_name = lastName.value)

        personRepository.insert(newPerson)

        eventRepository.insert(
            TrackrEvent(
                newPerson.id,
                eventType,
                eventDate.value
                    .toEpochDay(),
                7,
                0)
        )
    }
}
