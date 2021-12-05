package com.trackr.trackr_app.ui.calendar

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.trackr.trackr_app.ui.theme.Rubik
import java.time.LocalDate
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.*

/**
 * The Calendar Component - Displays a responsive kCalendar
 * @param selectedDate the selected date in the calendar
 * @param onSelect the function to invoke when a new date is selected. This function takes an int
 * as an argument, which represents the new selected date
 * @param eventDates the dates of all the events in the selected month
 * @param onSwipe the function to invoke when the previous month and next month buttons are clicked.
 * This function takes in an integer (1 or -1) to represent the next month or previous month.
 */
@Composable
fun Calendar(
    selectedDate: LocalDate,
    eventDates: Set<LocalDate>,
    modifier: Modifier = Modifier,
    onSelect: (Int) -> Unit = {},
    onSwipe: (Long) -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Icon(
                Icons.Filled.NavigateBefore,
                "Previous Month",
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 12.dp)
                    .clickable { onSwipe(-1) }
            )
            Text(
                selectedDate
                    .month
                    .getDisplayName(TextStyle.FULL, Locale.getDefault()) +
                        " ${selectedDate.year}",
                fontFamily = Rubik,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .padding(10.dp)
            )
            Icon(
                Icons.Filled.NavigateNext,
                "Next Month",
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 12.dp)
                    .clickable { onSwipe(1) }
            )
        }
        CalendarHeader()
        CalendarMonth(
            selectedDate,
            eventDates,
        ) { onSelect(it) }
    }
}

/**
 * A body of the Calendar. This component displays all the calendar days and the names of the
 * weekdays.
 * @param selectedDate the date selected
 * @param eventDates the dates of all the events in the selected month
 * @param onSelect the function to invoke when a new date is selected
 */
@Composable
fun CalendarMonth(
    selectedDate: LocalDate,
    eventDates: Set<LocalDate>,
    onSelect: (Int) -> Unit = {},
) {
    val startOffset = selectedDate.withDayOfMonth(1).dayOfWeek.value % 7
    val endOffset = selectedDate.withDayOfMonth(selectedDate.lengthOfMonth()).dayOfWeek.value % 7
    val numberOfWeeks = selectedDate.withDayOfMonth(selectedDate.lengthOfMonth())
        .get(WeekFields.SUNDAY_START.weekOfMonth())

    Column {
        for (week in 0 until numberOfWeeks) {
            Row {
                val startDay = if (week == 0) startOffset else 0
                for (fillerDay in 1..startDay) {
                    CalendarGridContainer("")
                }
                val endDay = if (week == numberOfWeeks - 1) endOffset else 6
                for (day in startDay..endDay) {
                    val dayOfMonth = week * 7 + day + 1 - startOffset
                    CalendarGridContainer(
                        dayOfMonth.toString(),
                        selectedDate.dayOfMonth == dayOfMonth,
                        eventDates.contains(
                            LocalDate.of(2008, selectedDate.month, dayOfMonth)
                        ),
                        onSelect
                    )
                }
            }
        }
    }
}

/**
 * A component that displays a row of weekday names.
 */
@Composable
fun CalendarHeader() {
    Row {
        CalendarGridContainer("Su")
        CalendarGridContainer("Mo")
        CalendarGridContainer("Tu")
        CalendarGridContainer("We")
        CalendarGridContainer("Th")
        CalendarGridContainer("Fr")
        CalendarGridContainer("Sa")
    }
}

/**
 * A component that contains a single day.
 * @param gridText the text (the date of the month) to be displayed in this component
 * @param isSelected a Boolean representing whether or not this date was selected
 * @param onSelect the function to invoke when this date is selected.
 */
@Composable
fun CalendarGridContainer(
    gridText: String,
    isSelected: Boolean = false,
    isInEventDates: Boolean = false,
    onSelect: (Int) -> Unit = {},
) {
    var modifier = Modifier.size(45.dp, 45.dp)

    if (isSelected) {
        modifier = modifier.border(
        2.dp,
        MaterialTheme.colors.onPrimary,
        CircleShape)
    }

    if (gridText.isNotEmpty()) {
        modifier = modifier.clickable {
            onSelect(
                if (gridText.isDigitsOnly()) gridText.toInt() else 0
            )
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = gridText,
            textAlign = TextAlign.Center,
            fontFamily = Rubik,
            fontSize = 18.sp,
            color = if (isInEventDates) Color.Red else MaterialTheme.colors.onPrimary,
            fontWeight = if (isInEventDates) FontWeight.Black else FontWeight.Normal,
        )
    }
}
