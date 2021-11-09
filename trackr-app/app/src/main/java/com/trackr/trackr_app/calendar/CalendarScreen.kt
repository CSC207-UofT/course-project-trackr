package com.trackr.trackr_app.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable

//@Preview
@Composable
fun CalendarScreen(
    calendarViewModel: CalendarViewModel,
) {
    val selectedDate = rememberSaveable { calendarViewModel.selectedDate }
    Column() {
        Calendar(
            selectedDate = selectedDate.value,
            { calendarViewModel.changeSelectedDate(it) },
            { calendarViewModel.changeMonth(it) }
        )
//        EventList(...)  // TODO
    }
}