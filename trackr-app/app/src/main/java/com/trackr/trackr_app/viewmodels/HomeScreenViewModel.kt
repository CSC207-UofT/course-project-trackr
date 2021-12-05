package com.trackr.trackr_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trackr.trackr_app.manager.SinglePersonAccessor
import com.trackr.trackr_app.repository.EventAccessor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

/**
 * A viewmodel that manages the state of the home screen view.
 * This viewmodel manages all the data and business logic required for
 * the homescreen to work.
 * @param eventAccessor the EventAccessor instance used to access all events from the database
 * @param singlePersonAccessor the SinglePersonAccesor instance used to retrieve person data from the
 * database
 */
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val eventAccessor: EventAccessor,
    private val singlePersonAccessor: SinglePersonAccessor,
) : ViewModel() {
    private val _allEvents: MutableLiveData<List<TrackrEventOutput>> = MutableLiveData(listOf())
    val allEvents: LiveData<List<TrackrEventOutput>> get() = _allEvents

    private val _eventsToday: MutableLiveData<List<TrackrEventOutput>> = MutableLiveData(listOf())
    val eventsToday: LiveData<List<TrackrEventOutput>> get() = _eventsToday

    /**
     * Initialize the homepage so that it can display all events and events today.
     * Fetch all events and all events today.
     */
    init {
        updateHomeScreenData()
    }

    /**
     * Fetch the most update-to-date data from the database to display on the homescreen
     */
    fun updateHomeScreenData() {
        viewModelScope.launch {
            eventAccessor.getAllEvents().collectLatest {
                val allEventsList = mutableListOf<TrackrEventOutput>()

                for (event in it) {
                    allEventsList.add(
                        TrackrEventOutput(
                            event,
                            singlePersonAccessor.getPersonById(event.personId),
                            Calendar.getInstance().get(Calendar.YEAR)
                        )
                    )
                }
                _allEvents.value = allEventsList
            }
        }

        viewModelScope.launch {
            eventAccessor.listFromRange(
                LocalDate.now().withYear(2008),
                LocalDate.now().withYear(2008)
            ).collectLatest {
                val eventsTodayList = mutableListOf<TrackrEventOutput>()
                for (event in it) {
                    eventsTodayList.add(
                        TrackrEventOutput(
                            event,
                            singlePersonAccessor.getPersonById (event.personId),
                        Calendar.getInstance().get(Calendar.YEAR)
                    )
                    )
                }
                _eventsToday.value = eventsTodayList
            }
        }
    }
}
