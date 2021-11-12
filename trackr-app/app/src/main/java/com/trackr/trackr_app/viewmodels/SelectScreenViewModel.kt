package com.trackr.trackr_app.viewmodels

import androidx.lifecycle.*
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.repository.EventRepository
import kotlinx.coroutines.launch

class SelectScreenViewModel(private val eventRepository: EventRepository): ViewModel() {
    val allEvents: LiveData<List<TrackrEvent>> = eventRepository.allEvents.asLiveData()
}

class SelectScreenViewModelFactory(private val repository: EventRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SelectScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SelectScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
