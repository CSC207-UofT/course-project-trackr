package com.trackr.trackr_app.viewmodels

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val eventRepository: EventRepository,
): ViewModel() {
    private val _selectedDate: MutableState<LocalDate> = mutableStateOf(LocalDate.now())
    val selectedDate: State<LocalDate> get() = _selectedDate

    val selectedEvents get() = _selectedEvents
    private var _selectedEvents: LiveData<List<TrackrEvent>> = eventRepository
        .listFromRange(
            _selectedDate.value.withYear(1970),
            _selectedDate.value.withYear(1970)
        )
        .asLiveData()

    private val eventsThisMonth = eventRepository
        .listFromRange(
            _selectedDate.value.withYear(1970),
            _selectedDate.value.withYear(1970)
        )

    private var eventDays: Set<LocalDate> = HashSet()

    init {
        viewModelScope.launch {

        }
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
        _selectedEvents = eventRepository
            .listFromRange(
                _selectedDate.value.withYear(1970),
                _selectedDate.value.withYear(1970)
            )
            .asLiveData()
    }

    /**
     * Change the selected date to the newDay
     * @param newDay the new day of the month
     */
    fun changeSelectedDate(newDay: Int) {
        _selectedDate.value = _selectedDate.value.withDayOfMonth(newDay)
        _selectedEvents = eventRepository
        .listFromRange(
            _selectedDate.value.withYear(1970),
            _selectedDate.value.withYear(1970)
        )
            .asLiveData()
    }
}