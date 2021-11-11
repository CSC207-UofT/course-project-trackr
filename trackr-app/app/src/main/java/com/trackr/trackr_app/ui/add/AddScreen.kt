package com.trackr.trackr_app.ui.add

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.trackr.trackr_app.ui.home.HomeScreenViewModel
import com.trackr.trackr_app.ui.shared.InputWidget
import com.trackr.trackr_app.ui.shared.InteractiveDropdownWidget

@Composable
fun AddScreenActivity(
    viewModel: HomeScreenViewModel,
    nav: NavHostController
) {
    val events: List<List<Any>> by viewModel.events.observeAsState(listOf())
    AddScreen(onAddItem = {viewModel.addEvent(it)}, nav = nav)
}

@Composable
fun AddScreen(
    onAddItem: (List<Any>) -> Unit, nav: NavHostController
) {
    var eventName by remember { mutableStateOf("Name") }
    var chosenMonth by remember { mutableStateOf("Jan") }
    var chosenDay by remember { mutableStateOf(1) }
    var chosenReminder by remember { mutableStateOf("1 day before") }

    val months = listOf(
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
    )

    Scaffold(
    ) {
        Column(
            Modifier.padding(20.dp)
        ) {
            InputWidget(title = "Whose birthday is it again?") {
                TextField(
                    value = eventName,
                    onValueChange = { eventName = it },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black
                    )
                )
            }
            InputWidget(title = "Date", widgets = listOf(
                {InteractiveDropdownWidget(

                    setter = {month: String -> chosenMonth = month},
                    getter = {chosenMonth},
                    options = months
                    )
                },
                {InteractiveDropdownWidget(
                    setter = {day: Int -> chosenDay = day},
                    getter = {chosenDay},
                    options = (1..32).map{it}
                    )
                }
            )
            )
            InputWidget(title = "Remind Me") {
                InteractiveDropdownWidget(
                    setter = {reminder: String -> chosenReminder = reminder},
                    getter = {chosenReminder},
                    options = listOf(
                        "1 day before", "3 days before",
                        "1 week before", "2 weeks before", "1 month before"
                    )
                )
            }
            Button(
                onClick = {
                    onAddItem(listOf<Any>(eventName, chosenMonth, chosenDay, chosenReminder))
                    nav.navigate("Home")
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
