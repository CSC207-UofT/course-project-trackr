package com.trackr.trackr_app.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trackr.trackr_app.R
import com.trackr.trackr_app.TrackrApp
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.ui.select.SelectScreenActivity
import com.trackr.trackr_app.ui.edit.EditScreenActivity
import com.trackr.trackr_app.ui.calendar.CalendarScreenActivity
import com.trackr.trackr_app.ui.add.AddScreenActivity
import com.trackr.trackr_app.ui.calendar.CalendarViewModel
import com.trackr.trackr_app.ui.home.HomeScreenActivity
import com.trackr.trackr_app.ui.navigation.NavScreen
import com.trackr.trackr_app.viewmodels.HomeScreenViewModel
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.viewmodels.AddScreenViewModel
import com.trackr.trackr_app.viewmodels.EditScreenViewModel
import com.trackr.trackr_app.viewmodels.SelectScreenViewModel

@Composable
fun MainScreen(
    homeScreenViewModel: HomeScreenViewModel,
    addScreenViewModel: AddScreenViewModel,
    selectScreenViewModel: SelectScreenViewModel,
    editScreenViewModel: EditScreenViewModel,
) {
    val navController = rememberNavController()
    var canGoBack by remember { mutableStateOf(false) }
    navController.addOnDestinationChangedListener { controller, _, _, ->
        canGoBack = controller.previousBackStackEntry != null
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
            ) {
                Box(
                    Modifier.padding(10.dp)
                ) {
                    Text(
                        stringResource(R.string.app_name),
                        fontFamily = Rubik,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (canGoBack) {
                        Icon(
                            Icons.Filled.ArrowBackIos,
                            "Back",
                            Modifier
                                .fillMaxHeight()
                                .clickable {
                                    navController.popBackStack()
                                },
                            MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "Home") {
            composable("Home") { HomeScreenActivity(homeScreenViewModel, navController) }
            composable("Add") { AddScreenActivity(addScreenViewModel, navController) }
            composable("Select") { SelectScreenActivity(selectScreenViewModel, navController) }
            composable("Edit/{userId}") { backStackEntry ->
                backStackEntry.arguments!!.getString("userId")?.let { it1 ->
                    EditScreenActivity(editScreenViewModel, navController,
                        it1
                    )
                }
            }
            composable("Calendar") {
                CalendarScreenActivity(CalendarViewModel(), navController)
            }
        }
    }
}