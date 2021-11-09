package com.trackr.trackr_app.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trackr.trackr_app.R
import com.trackr.trackr_app.calendar.CalendarScreen
import com.trackr.trackr_app.calendar.CalendarViewModel
import com.trackr.trackr_app.ui.home.HomeScreen
import com.trackr.trackr_app.ui.navigation.NavScreen
import com.trackr.trackr_app.ui.theme.Rubik

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Text(
                    stringResource(R.string.app_name),
                    fontFamily = Rubik,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) {
        NavHost(navController = navController, startDestination = NavScreen.Home.route) {
            composable(NavScreen.Home.route) { HomeScreen() }
            composable(NavScreen.Calendar.route) {
                val calendarViewModel: CalendarViewModel = viewModel()
                CalendarScreen(calendarViewModel)
            }
        }
    }
}