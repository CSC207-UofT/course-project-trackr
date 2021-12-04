package com.trackr.trackr_app.ui.people

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trackr.trackr_app.R
import com.trackr.trackr_app.ui.shared.EventList
import com.trackr.trackr_app.ui.shared.PersonList
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.viewmodels.AllPersonsScreenViewModel
import com.trackr.trackr_app.viewmodels.PersonOutput
import com.trackr.trackr_app.viewmodels.TrackrEventOutput

@Composable
fun AllPersonsScreenActivity(
        viewModel: AllPersonsScreenViewModel,
        navController: NavHostController
) {
    val personList by viewModel.allPersons.observeAsState(listOf())
    Scaffold(
            backgroundColor = MaterialTheme.colors.background,
            bottomBar = { BottomBarPersons(navController = navController) }
    ) {
        Column(
                modifier = Modifier.padding(20.dp)
        ) {
            PersonFeed(
                    Modifier
                            .padding(0.dp, 15.dp)
                            .weight(2f),
                    "Select a Person to View:",
                    personList,
                    navController
            )
        }
    }
}

@Composable
fun PersonFeed(modifier: Modifier,
               title: String,
               persons: List<PersonOutput>,
               nav: NavHostController) {
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
        if (persons.isEmpty()) {
            Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
            ) {
                Text(
                        stringResource(R.string.no_people),
                        fontFamily = Rubik,
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                )
            }
        } else {
            PersonList(
                    persons,
                    Modifier.fillMaxWidth(),
                    nav,
            )
        }
    }
}

@Composable
fun BottomBarPersons(
        navController: NavHostController
) {
    BottomNavigation(
            backgroundColor = MaterialTheme.colors.primary,
    ) {
        BottomNavigationItem(
                icon = {
                    Icon(
                            Icons.Filled.Add,
                            "Add Person",
                            tint = MaterialTheme.colors.onBackground,
                    )
                },
                label = { Text(text = "Add People") },
                selected = false,
                onClick = {
                    navController.navigate("AddPerson")
                    {
                        launchSingleTop = true
                    }
                }
        )
    }
}