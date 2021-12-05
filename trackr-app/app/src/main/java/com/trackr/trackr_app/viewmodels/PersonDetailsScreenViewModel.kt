package com.trackr.trackr_app.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.trackr.trackr_app.manager.PersonManager
import com.trackr.trackr_app.manager.PersonModifier
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * The view model for the PersonDetailsScreen which manages the data that appears on the
 * PersonDetails page
 * @param personModifier an object that is used to modify existing persons in the database
 * @param eventRepository an object that is used to read multiple events from the database
 * @param personRepository an object that is used to read multiple people from the database
 */
@HiltViewModel
class PersonDetailsScreenViewModel @Inject constructor(
        private val personModifier: PersonModifier,
        private val eventRepository: EventRepository,
        private val personRepository: PersonRepository,
        state: SavedStateHandle,

        ): ViewModel() {

    private val personID: String = state.get<String>("personId")!!

    private val _personEvents: MutableLiveData<List<TrackrEventOutput>> = MutableLiveData(listOf())
    val personEvents: LiveData<List<TrackrEventOutput>> get() = _personEvents

    private val _firstName = mutableStateOf("")
    val firstName: State<String> get() = _firstName

    private val _lastName = mutableStateOf("")
    val lastName: State<String> get() = _lastName

    init {
        viewModelScope.launch {
            val personInfo = personRepository.getPersonById(personID)
            _firstName.value = personInfo.firstName
            _lastName.value = personInfo.lastName
        }
        updatePersonDetailsEvents()
    }

    fun updatePersonDetailsEvents() {
        viewModelScope.launch {
            eventRepository.getByPersonId(personID).collectLatest {
                val personList = mutableListOf<TrackrEventOutput>()
                for (event in it) {
                    personList.add(TrackrEventOutput(event,
                        personRepository.getPersonById(personID),
                        Calendar.getInstance().get(Calendar.YEAR))
                    )
                }
                _personEvents.value = personList
            }
        }
    }

    fun editFirstName(newFirstName: String) = viewModelScope.launch {
        _firstName.value = newFirstName
    }

    fun editLastName(newLastName: String) = viewModelScope.launch {
        _lastName.value = newLastName
    }

    fun editPerson() = viewModelScope.launch {
        personModifier.editPerson(personID, _firstName.value, _lastName.value)
    }

    fun deletePerson() = viewModelScope.launch {
        personModifier.deletePerson(personID = personID)
    }
}