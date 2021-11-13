package com.trackr.trackr_app.viewmodels

import androidx.lifecycle.*
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class PersonSelectViewModel @Inject constructor(
    private val repository: PersonRepository
    ) : ViewModel() {
    val allPersons: LiveData<List<Person>> = repository.allPersons.asLiveData()

    fun insert(person: Person) = viewModelScope.launch {
        repository.insert(person)
    }

    fun delete(person: Person) = viewModelScope.launch {
        repository.delete(person)
    }

    fun editFirstName(new_first_name: String, person: Person) = viewModelScope.launch {
        repository.editFirstName(new_first_name, person)
    }

    fun editLastName(new_last_name: String, person: Person) = viewModelScope.launch {
        repository.editLastName(new_last_name, person)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}