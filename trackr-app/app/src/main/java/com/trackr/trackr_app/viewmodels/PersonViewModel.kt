package com.trackr.trackr_app.viewmodels

import androidx.lifecycle.*
import com.trackr.trackr_app.repository.PersonRepository
import com.trackr.trackr_app.model.Person
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class PersonViewModel(private val repository: PersonRepository) : ViewModel() {
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

class PersonViewModelFactory(private val repository: PersonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PersonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}