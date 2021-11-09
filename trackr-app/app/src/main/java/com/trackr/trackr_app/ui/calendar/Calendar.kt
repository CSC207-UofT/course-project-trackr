package com.trackr.trackr_app.ui.calendar

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trackr.trackr_app.R
import com.trackr.trackr_app.ui.theme.Rubik
import java.time.LocalDate
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.*


@Preview(
    uiMode = UI_MODE_NIGHT_YES,  // enables dark mode in preview]
)
@Composable
fun Preview() {
    var selectedDate by rememberSaveable { mutableStateOf(LocalDate.now()) }
    Calendar(
        selectedDate,
        { selectedDate = selectedDate.withDayOfMonth(it)} ,
        { selectedDate = selectedDate.plusMonths(it) }
    )
}

@Composable
fun Calendar(
    selectedDate: LocalDate,
    onSelect: (Int) -> Unit = {},
    onSwipe: (Long) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Icon(
                Icons.Filled.NavigateBefore,
                "Previous Month",
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
            )
            Text(
                selectedDate
                    .month
                    .getDisplayName(TextStyle.FULL, Locale.getDefault()) +
                        " ${selectedDate.year}",
                fontFamily = Rubik,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .padding(10.dp)
            )
            Icon(
                Icons.Filled.NavigateNext,
                "Next Month",
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
            )
        }
        CalendarHeader()
        CalendarMonth(
            selectedDate
        ) { onSelect(it) }
    }
}

@Composable
fun CalendarMonth(
    selectedDate: LocalDate,
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
                        onSelect
                    )
                }
            }
        }
    }
}

@Composable
fun CalendarHeader() {
    Row() {
        CalendarGridContainer(gridText = "Su")
        CalendarGridContainer(gridText = "Mo")
        CalendarGridContainer(gridText = "Tu")
        CalendarGridContainer(gridText = "We")
        CalendarGridContainer(gridText = "Th")
        CalendarGridContainer(gridText = "Fr")
        CalendarGridContainer(gridText = "Sa")
    }
}

@Composable
fun CalendarGridContainer(
    gridText: String,
    isSelected: Boolean = false,
    onSelect: (Int) -> Unit = {},
) {
    Box(
        Modifier.size(30.dp, 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = gridText,
            textAlign = TextAlign.Center,
            fontFamily = Rubik,
            fontSize = 12.sp,
            color = MaterialTheme.colors.onPrimary,
            modifier = if (gridText.isEmpty()) {
                Modifier.clickable { onSelect(gridText.toInt()) }
            } else {
                Modifier
            }
        )
    }
}
