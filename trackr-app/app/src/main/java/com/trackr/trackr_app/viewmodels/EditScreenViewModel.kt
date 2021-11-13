package com.trackr.trackr_app.viewmodels

import android.util.Log
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
    private val personRepository: PersonRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    val allEvents: LiveData<List<TrackrEvent>> = eventRepository.allEvents.asLiveData()


    /**
     * Edits/deletes an event using EventRepository
     *
     * @param data  list in the form [delete: String, new_month: String,
     *                                  new_date: String, new_interval: String, new_type: String]
     * @param id id of the event
     */
    fun editEvent(data: List<Any>, id: String) = viewModelScope.launch {
        val event = eventRepository.getById(id)

        if (data[0] == "Yes") {
            eventRepository.delete(event)
        } else {
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
            eventRepository.editInterval(reminderInterval, event)

            val date = LocalDate.of(2021, months.indexOf(data[1].toString()) + 1, data[2].toString().toInt())
            eventRepository.editDate(date, event)

            val eventType = if (data[4].toString() == "Birthday") 0 else 1
            eventRepository.editType(eventType, event)
        }
    }
}

class EditScreenViewModelFactory(
    private val eventRepository: EventRepository,
    private val personRepository: PersonRepository,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditScreenViewModel(eventRepository, personRepository, userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

