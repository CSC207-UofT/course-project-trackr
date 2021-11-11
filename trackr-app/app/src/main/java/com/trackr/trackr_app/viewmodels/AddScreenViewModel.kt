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


        //TODO: Unhardcode
        val randUserID = randomUUID()
        val randPersonID = randomUUID()
        val randEventID = randomUUID()
        userRepository.insert(User(id = randUserID.toString(), username = "yourmom"))

        personRepository.insert(Person(id = randPersonID.toString(), user_id = randUserID.toString(), first_name = "My", last_name = "Mom"))

        val calDate = Calendar.getInstance()
        calDate.set(2020, 1, 1)
        eventRepository.insert(TrackrEvent(data[0].toString(), randPersonID.toString(), 0,
            calDate.timeInMillis.toInt(), 7, 0))
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
