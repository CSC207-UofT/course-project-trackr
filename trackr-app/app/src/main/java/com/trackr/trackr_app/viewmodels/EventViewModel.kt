package com.trackr.trackr_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.trackr.trackr_app.model.Event
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.repository.EventRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class EventViewModel(private val repository: EventRepository) : ViewModel() {
    val allEvents: LiveData<List<Event>> = repository.allEvents.asLiveData()

    fun listFromRange(start_date: LocalDate, end_date: LocalDate) = viewModelScope.launch {
        repository.listFromRange(start_date, end_date)
    }

    fun insert(event: Event) = viewModelScope.launch {
        repository.insert(event)
    }

    fun delete(event: Event) = viewModelScope.launch {
        repository.delete(event)
    }

    fun editPerson(new_person: Person, event: Event) = viewModelScope.launch {
        repository.editPerson(new_person, event)
    }

    fun editType(new_type: Int, event: Event) = viewModelScope.launch{
        repository.editType(new_type, event)
    }

    fun editDate(new_date: LocalDate, event: Event) = viewModelScope.launch{
        repository.editDate(new_date, event)
    }

    fun editInterval(new_interval: Int, event: Event) = viewModelScope.launch{
        repository.editInterval(new_interval, event)
    }

    fun deleteAll() = viewModelScope.launch{
        repository.deleteAll()
    }
}