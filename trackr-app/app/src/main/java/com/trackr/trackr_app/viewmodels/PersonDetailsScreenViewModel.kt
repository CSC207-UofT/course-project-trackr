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

    /**
     * Initialize the allEvents list to display all events.
     */
    init {
        viewModelScope.launch {
            val personInfo = personRepository.getPersonById(personID)
            _firstName.value = personInfo.first_name
            _lastName.value = personInfo.last_name
            eventRepository.allEvents.collectLatest{
                val eventsPersonList = mutableListOf<TrackrEventOutput>()

                for (event in it) {
                    eventsPersonList.add(
                        TrackrEventOutput(
                            event,
                            personRepository.getPersonById(event.person_id),
                            Calendar.getInstance().get(Calendar.YEAR)
                        )
                    )
                }
                _personEvents.value = eventsPersonList
            }
        }
    }

    fun deletePerson() = viewModelScope.launch {
        personManager.deletePerson(personID = personID)
    }
}
