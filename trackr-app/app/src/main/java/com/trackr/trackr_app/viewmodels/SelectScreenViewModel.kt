package com.trackr.trackr_app.viewmodels

import androidx.lifecycle.*
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SelectScreenViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val personRepository: PersonRepository
    ): ViewModel() {
    val allEvents: MutableLiveData<List<TrackrEventOutput>> = MutableLiveData(listOf())

    /**
     * Initialize the allEvents list to display all events.
     */
    init {
        viewModelScope.launch {
            eventRepository.allEvents.collect {
                val eventList = mutableListOf<TrackrEventOutput>()
                for (event in it) {
                    eventList.add(
                        TrackrEventOutput(event,
                        personRepository.getPersonById(event.person_id),
                            Calendar.getInstance().get(Calendar.YEAR)
                        )
                    )
                }
                allEvents.value = eventList
            }
        }
    }
}
