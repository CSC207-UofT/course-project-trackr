package com.trackr.trackr_app.ui.edit

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trackr.trackr_app.ui.add.InputWidget
import com.trackr.trackr_app.ui.home.HomeScreenViewModel
import com.trackr.trackr_app.ui.theme.Rubik


@Composable
fun EditScreenActivity(viewModel: HomeScreenViewModel, nav: NavHostController, backStackEntry: String) {
    EditScreen(onEditItem = {viewModel.editEvent(it, backStackEntry.toInt())}, nav = nav, entry = backStackEntry, viewModel)
}

@Composable
fun EditScreen(
    onEditItem: (List<Any>) -> Unit, nav: NavHostController, entry: String, viewModel: HomeScreenViewModel
) {
    val events = viewModel.events.value
    val event = events!![entry.toInt()]

    var eventName by remember { mutableStateOf(event[0].toString()) }
    var chosenMonth by remember { mutableStateOf(event[1].toString()) }
    var chosenDay by remember { mutableStateOf(event[2].toString().toInt()) }
    var chosenReminder by remember { mutableStateOf(event[3].toString()) }

    val months = listOf<String>(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )

    Scaffold(
    ) {
        Column(
            Modifier.padding(20.dp)
        ) {
            Text(
                "Edit and Save Changes",
                fontFamily = Rubik,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(bottom = 25.dp),
            )
            InputWidget(title = "Event Name (i think)") {
                TextField(
                    value = eventName,
                    onValueChange = { eventName = it },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black
                    )
                )
            }
            com.trackr.trackr_app.ui.edit.InputWidget(title = "Date", widgets = listOf(
                {
                    InteractiveDropdown(
                        setter = {month: String -> chosenMonth = month},
                        getter = {chosenMonth},
                        options = months)
                },
                {
                    InteractiveDropdown(
                        setter = {day: Int -> chosenDay = day},
                        getter = {chosenDay},
                        options = (1..32).map{it}
                    )
                }
            ))
            com.trackr.trackr_app.ui.edit.InputWidget(title = "Remind Me") {
                InteractiveDropdown(
                    setter = {reminder: String -> chosenReminder = reminder},
                    getter = {chosenReminder},
                    options = listOf<String>(
                        "1 day before", "3 days before",
                        "1 week before", "2 weeks before", "1 month before"
                    ))
            }
            Button(onClick = { onEditItem(listOf<Any>(eventName, chosenMonth, chosenDay, chosenReminder)); nav.navigate("Home")}, Modifier.padding(top = 20.dp), ) {
                Text("Save Changes")
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Icon(
                    Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }
    }
}

@Composable
fun InputWidget(title: String, widgets: List<@Composable() () -> Unit>) {
    Column() {
        Text(text = title, Modifier.padding(bottom = 5.dp), fontWeight = FontWeight.Bold)
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
    com.trackr.trackr_app.ui.edit.InputWidget(title = title, widgets = listOf(widget))
}

@Composable
fun <T>InteractiveDropdown(setter: (T) -> Unit, getter: () -> T, options: List<T>) {
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



