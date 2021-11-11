package com.trackr.trackr_app.viewmodels

import androidx.lifecycle.*
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.repository.EventRepository
import kotlinx.coroutines.launch
import java.sql.Date
import java.util.*

class EditScreenViewModel(private val eventRepository: EventRepository): ViewModel() {
    val allEvents: LiveData<List<TrackrEvent>> = eventRepository.allEvents.asLiveData()

    // TODO: This doesn't make any sense to me, I've basically just copy-pasted the add method from AddScreenViewModel
    fun editEvent(data: List<Any>, index: Int) = viewModelScope.launch {
        val calDate = Calendar.getInstance()
        calDate.set(data[1] as Int, data[2] as Int)
        eventRepository.insert(TrackrEvent("SomeID", "Some Person", 0,
            calDate.timeInMillis.toInt(), data[3] as Int, 0))
    }
}

class EditScreenViewModelFactory(private val repository: EventRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
