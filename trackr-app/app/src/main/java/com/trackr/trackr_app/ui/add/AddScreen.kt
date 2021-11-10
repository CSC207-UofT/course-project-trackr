package com.trackr.trackr_app.ui.add

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.trackr.trackr_app.ui.home.HomeScreenViewModel


@Composable
fun AddScreenActivity(
    viewModel: HomeScreenViewModel,
    nav: NavHostController
) {
    val events: List<List<Any>> by viewModel.events.observeAsState(listOf())
    AddScreen(onAddItem = {viewModel.addEvent(it)}, nav = nav)
}

@Composable
fun InputWidget(
    title: String,
    widgets: List<@Composable() () -> Unit>
) {
    Column() {
        Text(
            text = title,
            Modifier.padding(bottom = 5.dp),
            fontWeight = FontWeight.Bold
        )
        Row() {
            for (widget in widgets) {
                Box(
                    Modifier
                        .padding(bottom = 20.dp, end = 5.dp)
                        .border(
                            width = 3.dp, brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF3E69FF),
                                    Color(0xFF6CCFF8)
                                )
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )){
                    widget()
                }
            }
        }
    }
}

@Composable
fun InputWidget(title: String, widget: @Composable() () -> Unit) {
    InputWidget(title = title, widgets = listOf(widget))
}

@Composable
fun <T>InteractiveDropdown(
    setter: (T) -> Unit,
    getter: () -> T,
    options: List<T>
) {
    var expanded by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(getter().toString(), Modifier.padding(start = 15.dp))
        IconButton(onClick = { expanded = true }) {
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = "Localized description"
            )
        }
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        for (option in options) {
            DropdownMenuItem(onClick = {
                setter(option); expanded = false
            }) {
                Text(option.toString())
            }
        }
    }
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
                {
                    InteractiveDropdown(
                    setter = {month: String -> chosenMonth = month},
                    getter = {chosenMonth},
                    options = months
                    )
                },
                {
                    InteractiveDropdown(
                    setter = {day: Int -> chosenDay = day},
                    getter = {chosenDay},
                    options = (1..32).map{it}
                    )
                }
            )
            )
            InputWidget(title = "Remind Me") {
                InteractiveDropdown(
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
