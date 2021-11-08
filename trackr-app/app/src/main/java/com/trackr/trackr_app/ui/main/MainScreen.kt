package com.trackr.trackr_app.ui.main

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trackr.trackr_app.R
import com.trackr.trackr_app.entities.Event
import com.trackr.trackr_app.ui.home.HomeScreen
import com.trackr.trackr_app.ui.navigation.NavScreen
import com.trackr.trackr_app.ui.theme.Rubik

@Composable
fun MainScreen(eventList: List<String>, onAddItem: (String) -> Unit) {
    val navController = rememberNavController()
    //val eventList = ArrayList<String>()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAddItem("Bob's Birthday")
                    Log.d("HELLO", eventList.toString())
                          },
                backgroundColor = Color.Blue) {
                Icon(Icons.Filled.Add, "Add event")
            }
        },
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
            composable(NavScreen.Home.route) { HomeScreen(eventList = eventList) }
        }
    }
}