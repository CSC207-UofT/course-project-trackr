package com.trackr.trackr_app.ui.add

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.android.material.button.MaterialButton
import com.trackr.trackr_app.R
import com.trackr.trackr_app.ui.home.HomeScreenViewModel
import com.trackr.trackr_app.ui.main.MainScreen
import com.trackr.trackr_app.ui.navigation.NavScreen
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.ui.theme.TrackrappTheme




@Composable
fun AddScreenActivity(viewModel: HomeScreenViewModel, nav: NavHostController) {
    val events: List<List<Any>> by viewModel.events.observeAsState(listOf())
    AddScreen(onAddItem = {viewModel.addEvent(it)}, nav = nav)
}

@Composable
fun InputWidget(title: String, widgets: List<@Composable() () -> Unit>) {
    Column() {
        Text(text = title, Modifier.padding(bottom = 5.dp), fontWeight = FontWeight.Bold)
        Row() {
            for (widget in widgets) {
                Box(Modifier.padding(bottom = 20.dp, end = 5.dp).border(width = 3.dp, brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF3E69FF),
                        Color(0xFF6CCFF8)
                    )),
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
fun AddScreen(
    onAddItem: (List<Any>) -> Unit, nav: NavHostController
) {
    var eventName by remember { mutableStateOf("Name") }
    var expandedMonth by remember { mutableStateOf(false) }
    var expandedDay by remember { mutableStateOf(false) }
    var expandedReminder by remember { mutableStateOf(false) }
    var chosenMonth by remember { mutableStateOf("Jan") }
    var chosenDay by remember { mutableStateOf(1) }
    var chosenReminder by remember { mutableStateOf("1 day before") }

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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(chosenMonth, Modifier.padding(start = 15.dp))
                        IconButton(onClick = { expandedMonth = true }) {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = expandedMonth,
                        onDismissRequest = { expandedMonth = false }
                    ) {
                        val months = listOf<String>(
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
                        for (month in months) {
                            DropdownMenuItem(onClick = {
                                chosenMonth = month; expandedMonth = false
                            }) {
                                Text(month)
                            }
                        }
                    }
                },
                {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(chosenDay.toString(), Modifier.padding(start = 15.dp))
                        IconButton(onClick = { expandedDay = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Date")
                        }
                    }
                    DropdownMenu(
                        expanded = expandedDay,
                        onDismissRequest = { expandedDay = false }
                    ) {
                        for (day in 1 until 32) {
                            DropdownMenuItem(onClick = { chosenDay = day; expandedDay = false }) {
                                Text(day.toString())
                            }
                        }
                    }
                }
            ))
            InputWidget(title = "Remind Me") {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(chosenReminder, Modifier.padding(start = 15.dp))
                    IconButton(onClick = { expandedReminder = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Remind me")
                    }
                }
                DropdownMenu(
                    expanded = expandedReminder,
                    onDismissRequest = { expandedReminder = false }
                ) {
                    val months = listOf<String>(
                        "1 day before", "3 days before",
                        "1 week before", "2 weeks before", "1 month before"
                    )
                    for (month in months) {
                        DropdownMenuItem(onClick = {
                            chosenReminder = month; expandedReminder = false
                        }) {
                            Text(month)
                        }
                    }
                }
            }
            Button(onClick = { onAddItem(listOf<Any>(eventName, chosenMonth, chosenDay, chosenReminder)); nav.navigate("Home")}, Modifier.padding(top = 20.dp), ) {
                Text("Save Event")
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
fun BottomAppBar() {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
    ) {
    }
}
