package com.trackr.trackr_app.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Event
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.trackr.trackr_app.R
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.ui.add.AddScreenActivity
import com.trackr.trackr_app.ui.main.MainScreen
import com.trackr.trackr_app.ui.navigation.NavScreen
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.ui.theme.TrackrappTheme
import com.trackr.trackr_app.ui.theme.allGradients
import com.trackr.trackr_app.ui.theme.blueGradient
import com.trackr.trackr_app.viewmodels.HomeScreenViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


val temp_data = listOf(1,2,3,4,5)
//@Preview(
//    uiMode = UI_MODE_NIGHT_YES  // enables dark mode for preview
//)
//@Composable
//private fun PreviewWithTheme() {
//    TrackrappTheme {
//        HomeScreen(listOf("hello", "hi"))
//    }
//}

@Composable
fun HomeScreenActivity(
    viewModel: HomeScreenViewModel,
    navController: NavHostController
) {
    val events: List<TrackrEvent> by viewModel.allEvents.observeAsState(listOf())
    HomeScreen(
        // TODO: figure out how to move this to the viewModel
        eventList = events.map {
            val dateTime = LocalDate.ofEpochDay(it.date)
            listOf(it.id, dateTime.month, dateTime.dayOfMonth, it.reminder_interval) },
        viewModel = viewModel,
        navController = navController)
}

@Composable
fun HomeScreen(
    eventList: List<List<Any>>,
    viewModel: HomeScreenViewModel,
    navController: NavHostController
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("Add")
                },
                backgroundColor = MaterialTheme.colors.onBackground,
                contentColor = MaterialTheme.colors.background,
            ) {
                Icon(Icons.Filled.Add, "Add event")
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = MaterialTheme.colors.background,
        bottomBar = {
            BottomAppBar(
                navController,
            )
        }
    ) {
       Column(
           modifier = Modifier
               .padding(20.dp)
       ) {
           HomeFeed(
               Modifier
                   .padding(0.dp, 10.dp)
                   .weight(1f),
               stringResource(R.string.todays_events),
               listOf(),
               navController
           )
           HomeFeed(
               Modifier
                   .padding(0.dp, 10.dp)
                   .weight(2f),
               stringResource(R.string.upcoming_events),
               eventList,
               navController
           )
       }
    }
}

@Composable
fun HomeFeed(
    modifier: Modifier,
    title: String,
    events: List<List<Any>>,
    navController: NavHostController,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            title,
            fontFamily = Rubik,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(bottom = 5.dp),
        )
        if (events.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    stringResource(R.string.no_events),
                    fontFamily = Rubik,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            EventList(
                events,
                modifier = Modifier.fillMaxWidth(),
                navController,
            )
        }
    }
}


@Composable
fun EventList(
    events: List<List<Any>>,
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(events.count()) { index ->
            val event = events[index]
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("Edit/${event[0]}")
                    },
                shape = RoundedCornerShape(20),
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = allGradients[index % 3]
                            )
                        )
                        .padding(20.dp)

                ) {
                    Column() {
                        Text(event.toString())
                    }
                    Column() {

                    }
                }
            }
        }
    }
}

@Composable
fun BottomAppBar(
    navController: NavHostController,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
    ) {

        BottomNavigationItem(
            selected = false,
            icon = { Icon(
                Icons.Filled.Event,
                "Calendar View",
                tint = MaterialTheme.colors.onBackground
            )},
            label = {Text("View Calendar", fontFamily = Rubik)},
            onClick = {
                navController.navigate("Calendar")
                {
                    launchSingleTop = true
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.Edit,
                    "Edit event",
                    tint = MaterialTheme.colors.onBackground,
                )
                   },
            label = { Text(text = "Edit Events") },
            selected = false,
            onClick = {
                navController.navigate("Select")
                {
                    launchSingleTop = true
                }
            }
        )
    }
}
