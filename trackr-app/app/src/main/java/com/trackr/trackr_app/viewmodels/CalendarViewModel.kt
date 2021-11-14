package com.trackr.trackr_app.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashSet

/**
 * A viewmodel that is responsible for managing the state of the calendar screen.
 */
@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val personRepository: PersonRepository,
): ViewModel() {
    private val _selectedDate: MutableState<LocalDate> = mutableStateOf(LocalDate.now())
    val selectedDate: State<LocalDate> get() = _selectedDate

    private var _selectedEvents: MutableLiveData<List<TrackrEventOutput>> = MutableLiveData(listOf())
    val selectedEvents: LiveData<List<TrackrEventOutput>> get() = _selectedEvents

    private val eventsThisMonth get() = eventRepository
        .listFromRange(
            _selectedDate.value.withYear(1970).withDayOfMonth(1),
            _selectedDate.value.withYear(1970).withDayOfMonth(_selectedDate.value.lengthOfMonth())
        )

    private var _eventDates: MutableLiveData<Set<LocalDate>> = MutableLiveData(HashSet())
    val eventDates: LiveData<Set<LocalDate>> get() = _eventDates
    
    init {
        updateSelectedEvents()
        updateEventDates()
    }
    
    /**
     * Increase the current month by monthOffset months.
     * If monthOffset is negative go back months, otherwise
     * increase the current month.
     * @param monthOffset the amount of months to add to the current month
     */
    fun changeMonth(monthOffset: Long) {
        _selectedDate.value = _selectedDate.value
            .plusMonths(monthOffset)
            .withDayOfMonth(1)
        updateSelectedEvents()
        updateEventDates()
    }

    /**
     * Change the selected date to the newDay
     * @param newDay the new day of the month
     */
    fun changeSelectedDate(newDay: Int) {
        if (_selectedDate.value.dayOfMonth != newDay) {
            _selectedDate.value = _selectedDate.value.withDayOfMonth(newDay)
            updateSelectedEvents()
        }
    }

    /**
     * Update the list of selected events to reflect any changes and new events added
     */
    private fun updateSelectedEvents() {
        viewModelScope.launch {
            eventRepository
                .listFromRange(
                    _selectedDate.value.withYear(1970),
                    _selectedDate.value.withYear(1970)
                )
                .collect {
                    val eventsOnSelectedDate = mutableListOf<TrackrEventOutput>()
                    for (event in it) {
                        eventsOnSelectedDate.add(
                            TrackrEventOutput(event,
                            personRepository.getPersonById(event.person_id),
                                Calendar.getInstance().get(Calendar.YEAR))
                        )
                    }
                    _selectedEvents.value = eventsOnSelectedDate
                }
        }
    }

    /**
     * Update the set of dates of all events this month
     */
    private fun updateEventDates() {
        viewModelScope.launch {
            eventsThisMonth.collect {
                val dates = HashSet<LocalDate>()
                for (event in it) {
                    dates.add(LocalDate.ofEpochDay(event.date))
                }
                _eventDates.value = dates
            }
        }
    }
}
