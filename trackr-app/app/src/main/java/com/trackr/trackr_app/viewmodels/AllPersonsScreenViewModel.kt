package com.trackr.trackr_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trackr.trackr_app.repository.PersonAccessor
import com.trackr.trackr_app.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model that manages the state of the 'all persons screen' view.
 * Manages the data and states required for the all persons screen to work.
 *
 * @param personAccessor the PersonAccessor instance used to access person data from
 * the database
 */
@HiltViewModel
class AllPersonsScreenViewModel @Inject constructor(
    private val personAccessor: PersonAccessor,
) : ViewModel() {
    private val _allPersons: MutableLiveData<List<PersonOutput>> = MutableLiveData(listOf())
    val allPersons: LiveData<List<PersonOutput>> get() = _allPersons

    /**
     * Initialize the allPersons list to display all people.
     */
    init {
        viewModelScope.launch {
            personAccessor.getAllPersons().collectLatest {
                val personList = mutableListOf<PersonOutput>()
                for (person in it) {
                    personList.add(PersonOutput(person))
                }
                _allPersons.value = personList
            }
        }
    }
}