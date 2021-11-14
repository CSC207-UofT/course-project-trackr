package com.trackr.trackr_app.ui.home

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trackr.trackr_app.R
import com.trackr.trackr_app.ui.shared.EventCard
import com.trackr.trackr_app.ui.shared.EventList
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.ui.theme.allGradients
import com.trackr.trackr_app.viewmodels.HomeScreenViewModel
import com.trackr.trackr_app.viewmodels.TrackrEventOutput
import java.time.format.TextStyle
import java.util.*


@Composable
fun HomeScreenActivity(
        viewModel: HomeScreenViewModel,
        navController: NavHostController
) {
    val allEvents by viewModel.allEvents.observeAsState(listOf())
    val eventsToday by viewModel.eventsToday.observeAsState(listOf())
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
                   .padding(vertical = 10.dp)
                   .weight(1f),
               stringResource(R.string.todays_events),
               eventsToday,
               navController
           )
           HomeFeed(
               Modifier
                   .padding(bottom = 40.dp)
                   .weight(2f),
               stringResource(R.string.upcoming_events),
               allEvents,
               navController
           )
       }
    }
}

@Composable
fun HomeFeed(
    modifier: Modifier,
    title: String,
    events: List<TrackrEventOutput>,
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
        EventList(
                events,
                modifier = Modifier.fillMaxWidth(),
                navController,
        )
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
