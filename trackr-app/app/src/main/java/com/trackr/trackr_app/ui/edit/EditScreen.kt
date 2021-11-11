package com.trackr.trackr_app.ui.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.ui.shared.InputWidget
import com.trackr.trackr_app.ui.shared.InteractiveDropdownWidget
import com.trackr.trackr_app.viewmodels.EditScreenViewModel
import java.util.*


@Composable
fun EditScreenActivity(viewModel: EditScreenViewModel, nav: NavHostController, backStackEntry: String) {
    EditScreen(onEditItem = {viewModel.editEvent(it, backStackEntry.toInt())}, nav = nav, entry = backStackEntry, viewModel)
}

@Composable
fun EditScreen(
    onEditItem: (List<Any>) -> Unit, nav: NavHostController, entry: String, viewModel: EditScreenViewModel
) {
    val events by viewModel.allEvents.observeAsState(listOf())

    val eventList = events.map {
        val calDate = Calendar.getInstance()
        calDate.time = it.date
        listOf(it.id, calDate.get(Calendar.MONTH), calDate.get(Calendar.DAY_OF_MONTH), it.reminder_interval) }

    val event = events!![entry.toInt()]

    var eventName by remember { mutableStateOf(eventList[0].toString()) }
    var chosenMonth by remember { mutableStateOf(eventList[1].toString()) }
    var chosenDay by remember { mutableStateOf(eventList[2].toString().toInt()) }
    var chosenReminder by remember { mutableStateOf(eventList[3].toString()) }

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
            InputWidget(title = "Date", widgets = listOf(
                {
                    InteractiveDropdownWidget(
                        setter = {month: String -> chosenMonth = month},
                        getter = {chosenMonth},
                        options = months)
                },
                {
                    InteractiveDropdownWidget(
                        setter = {day: Int -> chosenDay = day},
                        getter = {chosenDay},
                        options = (1..32).map{it}
                    )
                }
            ))
            InputWidget(title = "Remind Me") {
                InteractiveDropdownWidget(
                    setter = {reminder: String -> chosenReminder = reminder},
                    getter = {chosenReminder},
                    options = listOf<String>(
                        "1 day before", "3 days before",
                        "1 week before", "2 weeks before", "1 month before"
                    ))
            }
            Button(onClick = {
                onEditItem(listOf<Any>(eventName, chosenMonth, chosenDay, chosenReminder))
                nav.navigate("Home")
                             }, Modifier.padding(top = 20.dp), ) {
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