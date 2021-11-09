package com.trackr.trackr_app.calendar

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trackr.trackr_app.ui.home.EventList


@Preview
@Composable
fun CalendarScreenActivity(
    calendarViewModel: CalendarViewModel = CalendarViewModel(),
) {
    val selectedDate = rememberSaveable { calendarViewModel.selectedDate }
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Calendar(
            selectedDate = selectedDate.value,
            Modifier.fillMaxSize().weight(1f),
            { calendarViewModel.changeSelectedDate(it) },
            { calendarViewModel.changeMonth(it) }
        )
        EventList(
            listOf(1,2,3,4,5),
                Modifier.weight(1.2f)
        )  // TODO
    }
}