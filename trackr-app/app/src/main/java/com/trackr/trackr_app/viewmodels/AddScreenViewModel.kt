package com.trackr.trackr_app.viewmodels

import androidx.lifecycle.*
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.repository.EventRepository
import kotlinx.coroutines.launch
import java.sql.Date
import java.util.*

class AddScreenViewModel(private val eventRepository: EventRepository): ViewModel() {
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

        // TODO un-hard-code these properties
        val calDate = Calendar.getInstance()
        calDate.set(2020, 1, 1)
        eventRepository.insert(TrackrEvent("SomeID", "Some Person", 0,
            calDate.timeInMillis.toInt(), 7, 0))
    }
}

class AddScreenViewModelFactory(private val repository: EventRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
