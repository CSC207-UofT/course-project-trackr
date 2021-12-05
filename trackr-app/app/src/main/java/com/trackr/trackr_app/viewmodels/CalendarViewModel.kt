package com.trackr.trackr_app.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import kotlin.collections.HashSet

/**
 * A viewmodel that is responsible for managing the state and the business logic of the
 * calendar screen.
 * @param eventRepository an instance of the EventRepository used to fetch event data from
 * the database
 * @param personRepository an instance of the PersonRepository used to fetch person data from
 * the database.
 */
@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val personRepository: PersonRepository,
) : ViewModel() {

    private val _selectedDate: MutableState<LocalDate> = mutableStateOf(LocalDate.now())
    val selectedDate: State<LocalDate> get() = _selectedDate

    private var _selectedEvents: MutableLiveData<List<TrackrEventOutput>> =
        MutableLiveData(listOf())
    val selectedEvents: LiveData<List<TrackrEventOutput>> get() = _selectedEvents

    private var _eventDates: MutableLiveData<Set<LocalDate>> = MutableLiveData(HashSet())
    val eventDates: LiveData<Set<LocalDate>> get() = _eventDates

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
    fun updateSelectedEvents() {
        viewModelScope.launch {
            _selectedEvents.value = eventRepository
                .getEventsInRange(
                    _selectedDate.value.withYear(2008),
                    _selectedDate.value.withYear(2008)
                ).map {
                    TrackrEventOutput(
                        it,
                        personRepository.getPersonById(it.personId),
                        LocalDate.now().year
                    )
                }
        }
    }

    /**
     * Update the set of dates of all events this month
     */
    fun updateEventDates() {
        viewModelScope.launch {
            _eventDates.value = eventRepository
                .getEventsInRange(
                    _selectedDate.value.withYear(2008).withDayOfMonth(1),
                    _selectedDate.value.withYear(2008).withDayOfMonth(
                        _selectedDate.value.withYear(2008).lengthOfMonth()
                    )
                ).map { LocalDate.ofEpochDay(it.date) }.toSet()
        }
    }
}
