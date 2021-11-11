package com.trackr.trackr_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.repository.EventRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class EventViewModel(private val repository: EventRepository) : ViewModel() {
    val allEvents: LiveData<List<TrackrEvent>> = repository.allEvents.asLiveData()

    fun listFromRange(start_date: LocalDate, end_date: LocalDate) = viewModelScope.launch {
        repository.listFromRange(start_date, end_date)
    }

    fun insert(trackrEvent: TrackrEvent) = viewModelScope.launch {
        repository.insert(trackrEvent)
    }

    fun delete(trackrEvent: TrackrEvent) = viewModelScope.launch {
        repository.delete(trackrEvent)
    }

    fun editPerson(new_person: Person, trackrEvent: TrackrEvent) = viewModelScope.launch {
        repository.editPerson(new_person, trackrEvent)
    }

    fun editType(new_type: Int, trackrEvent: TrackrEvent) = viewModelScope.launch{
        repository.editType(new_type, trackrEvent)
    }

    fun editDate(new_date: LocalDate, trackrEvent: TrackrEvent) = viewModelScope.launch{
        repository.editDate(new_date, trackrEvent)
    }

    fun editInterval(new_interval: Int, trackrEvent: TrackrEvent) = viewModelScope.launch{
        repository.editInterval(new_interval, trackrEvent)
    }

    fun deleteAll() = viewModelScope.launch{
        repository.deleteAll()
    }
}