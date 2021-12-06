package com.trackr.trackr_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trackr.trackr_app.manager.SinglePersonAccessor
import com.trackr.trackr_app.repository.EventAccessor
import com.trackr.trackr_app.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * A viewmodel that manages the state of the event select screen.
 * This viewmodel is responsible for all the business logic required for the select screen.
 * @param eventAccessor an instance of the EventRepository used to fetch event data
 * @param singlePersonAccessor an instance of the SinglePersonAccessor used to access the data of a single person
 */
@HiltViewModel
class SelectScreenViewModel @Inject constructor(
    private val eventAccessor: EventAccessor,
    private val singlePersonAccessor: SinglePersonAccessor
) : ViewModel() {
    private val _allEvents: MutableLiveData<List<TrackrEventOutput>> = MutableLiveData(listOf())
    val allEvents: LiveData<List<TrackrEventOutput>> get() = _allEvents

    /**
     * Initialize the allEvents list to display all events.
     */
    init {
        viewModelScope.launch {
            eventAccessor.getAllEvents().collectLatest {
                val eventList = mutableListOf<TrackrEventOutput>()
                for (event in it) {
                    eventList.add(
                        TrackrEventOutput(
                            event,
                            singlePersonAccessor.getPersonById(event.personId),
                            Calendar.getInstance().get(Calendar.YEAR)
                        )
                    )
                }
                _allEvents.value = eventList
            }
        }
    }
}
