package com.trackr.trackr_app.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trackr.trackr_app.R
import com.trackr.trackr_app.ui.shared.EventList
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.viewmodels.HomeScreenViewModel
import com.trackr.trackr_app.viewmodels.TrackrEventOutput


@Composable
fun HomeScreenActivity(
    viewModel: HomeScreenViewModel,
    navController: NavHostController
) {
    val allEvents by viewModel.allEvents.observeAsState(listOf())
    val eventsToday by viewModel.eventsToday.observeAsState(listOf())
    viewModel.updateHomeScreenData()

    Scaffold(
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
                "Events This Year:",
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
            icon = {
                Icon(
                    Icons.Filled.Event,
                    "Calendar View",
                    tint = MaterialTheme.colors.onBackground
                )
            },
            label = { Text("Calendar", fontFamily = Rubik) },
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
                    Icons.Filled.Add,
                    "Add event",
                    tint = MaterialTheme.colors.onBackground,
                )
            },
            label = { Text(text = "Add Events") },
            selected = false,
            onClick = {
                navController.navigate("SelectPerson")
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
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.ManageAccounts,
                    "Edit people",
                    tint = MaterialTheme.colors.onBackground,
                )
            },
            label = { Text(text = "Edit People") },
            selected = false,
            onClick = {
                navController.navigate("AllPersons")
                {
                    launchSingleTop = true
                }
            }
        )
    }
}
