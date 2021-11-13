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
import com.trackr.trackr_app.ui.add.MONTHS
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

    private val _personName = mutableStateOf("")
    val personName: State<String>
        get() = _personName

    private val _eventName = mutableStateOf("Birthday")
    val eventName: State<String> get() = _eventName

    private var eventType: Int = 0

    private val _eventDate = mutableStateOf(LocalDate.of(2020, 1, 1))
    val eventDate: State<LocalDate> get() = _eventDate

    private val _chosenReminder = mutableStateOf("1 day before reminder")
    val chosenReminder: State<String> get() = _chosenReminder

    fun editName(newName: String) {
        _personName.value = newName
    }

    fun editEventName(newEventName: String) {
        _eventName.value = newEventName
        eventType = if (newEventName == "Birthday") 0 else 1
    }

    fun changeMonth(newMonth: String) {
        _eventDate.value = _eventDate.value
            .withMonth(MONTHS.indexOf(newMonth) + 1)
            .withDayOfMonth(_eventDate.value.dayOfMonth)
    }

    fun changeDay(newDay: Int) {
        _eventDate.value = _eventDate.value.withDayOfMonth(newDay)
    }

    fun changeReminderInterval(newInterval: String) {
        _chosenReminder.value = newInterval
    }

    fun addEvent() = viewModelScope.launch {
        //TODO: Unhardcode
        val randUserID = randomUUID()
        val randPersonID = randomUUID()
        val randEventID = randomUUID()
        userRepository.insert(
            User(id = randUserID.toString(),
                username = "yourmom")
        )

        personRepository.insert(
            Person(id = randPersonID.toString(),
                user_id = randUserID.toString(),
                first_name = "My",
                last_name = "Mom")
        )

        eventRepository.insert(
            TrackrEvent(
                personName.value,  // randEventID.toString(),
                randPersonID.toString(),
                eventType,
                eventDate.value
                    .withYear(1970)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toEpochSecond()
                    .toInt(),
                7,
                0))
    }
}
