package com.trackr.trackr_app.ui.edit

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
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
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.ui.shared.InputWidget
import com.trackr.trackr_app.ui.shared.InteractiveDropdownWidget
import com.trackr.trackr_app.viewmodels.EditScreenViewModel
import java.time.Instant
import java.time.ZoneId.SHORT_IDS
import java.util.*


@Composable
fun EditScreenActivity(viewModel: EditScreenViewModel, nav: NavHostController, backStackEntry: String) {
    EditScreen(onEditItem = {viewModel.editEvent(it, backStackEntry)}, nav = nav)
}

@Composable
fun EditScreen(
    onEditItem: (List<Any>) -> Unit, nav: NavHostController
) {
    var toDelete by remember { mutableStateOf("No") }
    var chosenMonth by remember { mutableStateOf("Jan") }
    var chosenDay by remember { mutableStateOf(1) }
    var chosenReminder by remember { mutableStateOf("1 day before") }
    var eventType by remember { mutableStateOf("Birthday") }
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
            Text(text = "Do you want to DELETE this event?:", Modifier.padding(bottom = 5.dp), fontWeight = FontWeight.Bold)
            Row(Modifier.selectableGroup().padding(top = 5.dp, bottom = 20.dp)) {
                RadioButton(
                        selected = toDelete == "No",
                        onClick = { toDelete = "No" }
                )
                Text(text = "No", Modifier.padding(start = 5.dp, end = 20.dp))
                RadioButton(
                        selected = toDelete != "No",
                        onClick = { toDelete = "Yes" }
                )
                Text(text = "Yes", Modifier.padding(start = 5.dp))
            }
            Text(text = "If not:", Modifier.padding(bottom = 5.dp), fontWeight = FontWeight.Bold)
            Text(text = "Type of event:", Modifier.padding(bottom = 5.dp), fontWeight = FontWeight.Bold)
            Row(Modifier.selectableGroup().padding(top = 5.dp, bottom = 20.dp)) {
                RadioButton(
                        selected = eventType == "Birthday",
                        onClick = { eventType = "Birthday" }
                )
                Text(text = "Birthday", Modifier.padding(start = 5.dp, end = 20.dp))
                RadioButton(
                        selected = eventType != "Birthday",
                        onClick = { eventType = "Anniversary" }
                )
                Text(text = "Anniversary", Modifier.padding(start = 5.dp))
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
                        onEditItem(listOf<Any>(toDelete, chosenMonth, chosenDay, chosenReminder, eventType))
                        nav.navigate("Home")
                    },
                    Modifier.padding(top = 20.dp),
            ) {
                Text("Confirm Changes")
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Icon(
                        Icons.Filled.Check,
                        contentDescription = "Edit Event",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }
    }
}