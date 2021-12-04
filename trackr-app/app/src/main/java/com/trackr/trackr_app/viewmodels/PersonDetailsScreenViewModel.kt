package com.trackr.trackr_app.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.trackr.trackr_app.manager.PersonManager
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import javax.inject.Inject


@HiltViewModel
class PersonDetailsScreenViewModel @Inject constructor(
    private val personManager: PersonManager,
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
            _firstName.value = personInfo.first_name
            _lastName.value = personInfo.last_name
            eventRepository.getPersonsById(personID).collectLatest {
                val personList = mutableListOf<TrackrEventOutput>()
                for (event in it) {
                    personList.add(TrackrEventOutput(event,
                        personRepository.getPersonById(event.person_id),
                        Calendar.getInstance().get(Calendar.YEAR))
                    )
                }
                _personEvents.value = personList
            }
        }
    }

    fun editFirstName(newFirstName: String) {
        _firstName.value = newFirstName
    }

    fun editLastName(newLastName: String) {
        _lastName.value = newLastName
    }

    fun editPerson() = viewModelScope.launch {
        personManager.editPerson(personID, _firstName.value, _lastName.value)
    }

    fun deletePerson() = viewModelScope.launch {
        personManager.deletePerson(personID = personID)
    }
}
