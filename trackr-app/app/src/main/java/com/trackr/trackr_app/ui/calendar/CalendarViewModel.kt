package com.trackr.trackr_app.ui.calendar

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate


class CalendarViewModel: ViewModel() {
    var selectedDate: LocalDate by mutableStateOf(LocalDate.now())
        private set

    /**
     * Increase the current month by monthOffset months.
     * If monthOffset is negative go back months, otherwise
     * increase the current month.
     * @param monthOffset the amount of months to add to the current month
     */
    fun changeMonth(monthOffset: Long) {
        selectedDate = selectedDate
            .plusMonths(monthOffset)
            .withDayOfMonth(1)
    }

    /**
     * Change the selected date to the newDay
     * @param newDay the new day of the month
     */
    fun changeSelectedDate(newDay: Int) {
        selectedDate = selectedDate.withDayOfMonth(newDay)
    }
}