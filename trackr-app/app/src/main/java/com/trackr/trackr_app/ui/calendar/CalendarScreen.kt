package com.trackr.trackr_app.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trackr.trackr_app.ui.home.EventList
import com.trackr.trackr_app.ui.theme.Rubik
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*


@Preview
@Composable
fun CalendarScreenActivity(
    calendarViewModel: CalendarViewModel = CalendarViewModel(),
) {
    val selectedDate = rememberSaveable { calendarViewModel.selectedDate }
    Column (
        modifier = Modifier.padding(0.dp)
    ) {
        Calendar(
            selectedDate = selectedDate.value,
            Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(bottom = 10.dp)
                .background(MaterialTheme.colors.primaryVariant),
            { calendarViewModel.changeSelectedDate(it) },
            { calendarViewModel.changeMonth(it) }
        )
        EventOnDateHeader(
            selectedDate.value,
            Modifier.padding(bottom = 10.dp)
        )
        EventList(
            listOf(1,2,3,4,5),
            Modifier
                .weight(1f)
                .padding(horizontal = 30.dp)
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
//               "${date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())}, " +
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