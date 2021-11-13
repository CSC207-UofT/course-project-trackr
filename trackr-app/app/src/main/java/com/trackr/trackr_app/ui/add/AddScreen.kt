package com.trackr.trackr_app.ui.add

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.trackr.trackr_app.ui.shared.InputWidget
import com.trackr.trackr_app.ui.shared.InteractiveDropdownWidget
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.viewmodels.AddScreenViewModel
import java.time.format.TextStyle
import java.util.*


@Composable
fun AddScreenActivity(
    viewModel: AddScreenViewModel,
    nav: NavHostController
) {
    val firstName by viewModel.firstName
    val lastName by viewModel.lastName
    val eventDate by viewModel.eventDate
    val chosenReminder by viewModel.chosenReminder
    val eventName by viewModel.eventName

    Scaffold(
    ) {
        Column(
            Modifier.padding(20.dp)
        ) {
            Text(text = "Type of event:", Modifier.padding(bottom = 5.dp), fontWeight = FontWeight.Bold)
            Row(
                Modifier
                    .selectableGroup()
                    .padding(top = 5.dp, bottom = 20.dp)) {
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
            InputWidget(
                title = "Whose birthday/anniversary is it?",
                widgets = listOf(
                    {
                        TextField(
                            value = firstName,
                            onValueChange = { viewModel.editFirstName(it) },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                textColor = MaterialTheme.colors.onBackground,
                            ),
                            placeholder = { Text("First Name", fontFamily = Rubik) }
                        )
                    },
                    {
                        TextField(
                            value = lastName,
                            onValueChange = { viewModel.editLastName(it) },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                textColor = MaterialTheme.colors.onBackground,
                            ),
                            placeholder = { Text("Last Name", fontFamily = Rubik) }
                        )
                    }
                ),
                Modifier.weight(1/2f)
            )
            InputWidget(title = "Date", widgets = listOf(
                {InteractiveDropdownWidget(
                    setter = {month: String -> viewModel.changeMonth(month)},
                    getter = {eventDate.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())},
                    options = viewModel.getMonths()
                )
                },
                {InteractiveDropdownWidget(
                    setter = {day: Int -> viewModel.changeDay(day)},
                    getter = {eventDate.dayOfMonth},
                    options = (1..eventDate.lengthOfMonth()).map{it}
                )
                }
            )
            )
            InputWidget(title = "Remind Me") {
                InteractiveDropdownWidget(
                    setter = {reminder: String -> viewModel.changeReminderInterval(reminder)},
                    getter = {chosenReminder},
                    options = viewModel.getReminderIntervals()
                )
            }
            Button(
                onClick = {
                    viewModel.addEvent()
                    nav.popBackStack()
                },
                Modifier.padding(top = 20.dp),
            ) {
                Text("Save Event")
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Icon(
                    Icons.Filled.Check,
                    contentDescription = "Create Event",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }
    }
}