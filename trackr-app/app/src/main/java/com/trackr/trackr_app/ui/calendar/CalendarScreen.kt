package com.trackr.trackr_app.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trackr.trackr_app.ui.shared.EventList
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.viewmodels.CalendarViewModel
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
fun CalendarScreenActivity(
    calendarViewModel: CalendarViewModel,
    navController: NavHostController,
) {
    val selectedDate by calendarViewModel.selectedDate
    val events by calendarViewModel.selectedEvents.observeAsState(listOf())
    val eventDates by calendarViewModel.eventDates.observeAsState(setOf())
    calendarViewModel.updateSelectedEvents()
    calendarViewModel.updateEventDates()

    Column {
        Calendar(
            selectedDate,
            eventDates,
            Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .background(MaterialTheme.colors.primaryVariant),
            { calendarViewModel.changeSelectedDate(it) },
            { calendarViewModel.changeMonth(it) }
        )
        EventOnDateHeader(
            selectedDate,
            Modifier.padding(bottom = 10.dp)
        )
        EventList(
            events = events,
            Modifier
                .padding(horizontal = 30.dp),
            navController,
        )
    }
}

@Composable
fun EventOnDateHeader(
    date: LocalDate,
    modifier: Modifier = Modifier
) {
   Text(
       "Events on " +
               date.month.getDisplayName(TextStyle.FULL, Locale.getDefault()) +
               " ${date.dayOfMonth}, " +
               date.year,
       modifier = modifier.fillMaxWidth(),
       textAlign = TextAlign.Center,
       fontFamily = Rubik,
       fontWeight = FontWeight.Bold,
       fontSize = 19.sp,
   )
}