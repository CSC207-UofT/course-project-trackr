package com.trackr.trackr_app.viewmodels

import androidx.lifecycle.*
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.model.User
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.repository.UserRepository
import kotlinx.coroutines.launch
import java.sql.Date
import java.util.*
import java.util.UUID.randomUUID

class AddScreenViewModel(
    private val eventRepository: EventRepository,
    private val personRepository: PersonRepository,
    private val userRepository: UserRepository
): ViewModel() {
    fun addEvent(data: List<Any>) = viewModelScope.launch {
        val months = listOf(
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

        val personId = data[0]
        userRepository.insert(User(id = "1", username = "DefaultUser"))
        var reminderInterval = 1
        if (data[3] == "1 day before") {
            reminderInterval = 1
        } else if (data[3] == "3 days before") {
            reminderInterval = 3
        } else if (data[3] == "1 week before") {
            reminderInterval = 7
        } else if (data[3] == "2 weeks before") {
            reminderInterval = 14
        } else {
            reminderInterval = 30
        }

        personRepository.insert(Person(id = personId.toString(), user_id = "1", first_name = personId.toString(), last_name = personId.toString()))

        val calDate = Calendar.getInstance()
        calDate.clear()
        calDate.set(2021, months.indexOf(data[1].toString()) + 1, data[2].toString().toInt(), 0, 0, 0)
        val eventType = if (data[4].toString() == "Birthday") 0 else 1
        eventRepository.insert(TrackrEvent(data[0].toString(), personId.toString(), eventType,
            calDate.timeInMillis.toInt(), reminderInterval, 0))
    }
}

class AddScreenViewModelFactory(
    private val eventRepository: EventRepository,
    private val personRepository: PersonRepository,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddScreenViewModel(eventRepository, personRepository, userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
