package com.trackr.trackr_app.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    eventRepository: EventRepository,
    private val personRepository: PersonRepository,
    ): ViewModel() {
    private val _allEvents: MutableLiveData<List<TrackrEventOutput>> = MutableLiveData(listOf())
    val allEvents get() = _allEvents

    private val _eventsToday: MutableLiveData<List<TrackrEventOutput>> = MutableLiveData(listOf())
    val eventsToday get() = _eventsToday

    init {
        viewModelScope.launch {
            val allEventsList = mutableListOf<TrackrEventOutput>()
            eventRepository.allEvents.collect {
                allEventsList.clear()
                for (event in it) {
                    allEventsList.add(
                        TrackrEventOutput(
                            event,
                            personRepository.getPersonById(event.person_id),
                            Calendar.getInstance().get(Calendar.YEAR)
                        )
                    )
                }
                _allEvents.value = allEventsList
            }
        }
        viewModelScope.launch {
            val eventsTodayList = mutableListOf<TrackrEventOutput>()
            eventRepository.listFromRange(
                LocalDate.now().withYear(1970),
                LocalDate.now().withYear(1970)
            ).collect {
                eventsTodayList.clear()
                for (event in it) {
                    eventsTodayList.add(TrackrEventOutput(event,
                        personRepository.getPersonById(event.person_id),
                        Calendar.getInstance().get(Calendar.YEAR))
                    )
                }
                _eventsToday.value = eventsTodayList
            }
        }
    }
}
