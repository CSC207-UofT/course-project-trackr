package com.trackr.trackr_app.ui.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trackr.trackr_app.ui.shared.InputWidget
import com.trackr.trackr_app.ui.shared.InteractiveDropdownWidget
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.viewmodels.EditScreenViewModel
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*


@Composable
fun EditScreenActivity(
    viewModel: EditScreenViewModel,
    nav: NavHostController,
) {
    val eventDate by viewModel.eventDate
    val chosenReminder by viewModel.chosenReminder
    val eventName by viewModel.eventName
    val personName by viewModel.personName
    var isEditing by remember { mutableStateOf(false) }

    Scaffold {
        Column(
            Modifier.padding(20.dp)
        ) {
            Text(
                "$personName's $eventName",
                fontFamily = Rubik,
                fontWeight = FontWeight.Black,
                fontSize = 25.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                "on " +
                        eventDate.dayOfWeek.getDisplayName(
                            TextStyle.FULL,
                            Locale.getDefault()
                        ) + ", " +
                        eventDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " +
                        eventDate.dayOfMonth,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 5.dp, bottom = 25.dp)
            )
            if (isEditing) {
                EditScreen(
                    eventName,
                    eventDate,
                    chosenReminder,
                    nav,
                    viewModel
                ) { isEditing = false }
            } else {
                InfoScreen(
                    personName,
                    eventName,
                    eventDate,
                    chosenReminder,
                ) { isEditing = true }
            }
        }
    }
}


@Composable
fun EditScreen(
    eventName: String,
    eventDate: LocalDate,
    chosenReminder: String,
    nav: NavHostController,
    viewModel: EditScreenViewModel,
    stopEditing: () -> Unit
) {
    Column {
        Text(text = "Type of event:", Modifier.padding(bottom = 5.dp), fontWeight = FontWeight.Bold)
        Row(
            Modifier
                .selectableGroup()
                .padding(top = 5.dp, bottom = 20.dp)
        ) {
            RadioButton(
                selected = eventName == "Birthday",
                onClick = { viewModel.editEventName("Birthday") }
            )
            Text(text = "Birthday", Modifier.padding(start = 5.dp, end = 20.dp))
            RadioButton(
                selected = eventName == "Anniversary",
                onClick = { viewModel.editEventName("Anniversary") }
            )
            Text(text = "Anniversary", Modifier.padding(start = 5.dp))
        }
        InputWidget(title = "Date", widgets = listOf(
            {
                InteractiveDropdownWidget(
                    setter = { month: String -> viewModel.changeMonth(month) },
                    getter = {
                        eventDate.month.getDisplayName(
                            TextStyle.SHORT,
                            Locale.getDefault()
                        )
                    },
                    options = viewModel.getMonths()
                )
            },
            {
                InteractiveDropdownWidget(
                    setter = { day: Int -> viewModel.changeDay(day) },
                    getter = { eventDate.dayOfMonth },
                    options = (1..eventDate.lengthOfMonth()).map { it }
                )
            },
            {
                InteractiveDropdownWidget(
                    setter = { year: Int -> viewModel.changeYear(year) },
                    getter = { eventDate.year },
                    options = (2100 downTo 1900).map { it }
                )
            }
        )
        )
        InputWidget(title = "Remind Me") {
            InteractiveDropdownWidget(
                setter = { reminder: String -> viewModel.changeReminderInterval(reminder) },
                getter = { chosenReminder },
                options = viewModel.getReminderIntervals()
            )
        }
        Button(
            onClick = {
                viewModel.editEvent()
                stopEditing()
            },
            Modifier.padding(top = 20.dp),
        ) {
            Text("Save Changes")
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Icon(
                Icons.Filled.Check,
                contentDescription = "Edit Event",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
        Button(
            onClick = {
                viewModel.deleteEvent()
                nav.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
        ) {
            Text(
                text = "DELETE event",
                Modifier.padding(bottom = 5.dp),
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun InfoScreen(
    personName: String,
    eventName: String,
    eventDate: LocalDate,
    chosenReminder: String,
    startEditing: () -> Unit
) {
    Column {
        Text(
            text = "This Event is for:",
            Modifier.padding(bottom = 10.dp, top = 20.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary
        )
        Text(personName, Modifier.padding(bottom = 25.dp), fontSize = 15.sp)
        Text(
            text = "Type of event:",
            Modifier.padding(bottom = 10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary
        )
        Text(eventName, Modifier.padding(bottom = 25.dp), fontSize = 15.sp)
        Text(
            text = "Event Date:",
            Modifier.padding(bottom = 10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary
        )
        Text(
            eventDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()) + ", " +
                    eventDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " +
                    eventDate.dayOfMonth,
            Modifier.padding(bottom = 30.dp),
            fontSize = 15.sp
        )
        Text(
            text = "Reminder Me:",
            Modifier.padding(bottom = 10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary
        )
        Text(chosenReminder, Modifier.padding(bottom = 40.dp), fontSize = 15.sp)
        Button(
            onClick = {
                startEditing()
            },
        ) {
            Text(text = "Edit", fontSize = 20.sp)
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        }
    }
}