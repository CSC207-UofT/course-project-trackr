package com.trackr.trackr_app.ui.select

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trackr.trackr_app.R
import com.trackr.trackr_app.ui.home.HomeScreenViewModel
import com.trackr.trackr_app.ui.theme.Rubik


@Composable
fun SelectScreenActivity(viewModel: HomeScreenViewModel, nav: NavHostController) {
    val events: List<List<Any>> by viewModel.events.observeAsState(listOf())
    SelectScreen(eventList = events, nav = nav)
}

@Composable
fun SelectScreen(
    eventList: List<List<Any>>, nav: NavHostController
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            SelectFeed(
                Modifier
                    .padding(0.dp, 15.dp)
                    .weight(2f),
                "Select an Event to Edit:",
                eventList,
                nav
            )
        }
    }
}

@Composable
fun SelectFeed(modifier: Modifier, title: String, events: List<List<Any>>, nav: NavHostController) {
    val context = LocalContext.current
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
            SelectList(events, nav)
        }
    }
}

@Composable
fun SelectList(events: List<Any>, nav: NavHostController) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(events) { event ->
            Surface(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20),
            ) {
                Row(
                    modifier = Modifier
                        .clickable { nav.navigate("Edit/${events.indexOf(event)}") }
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF3E69FF),
                                    Color(0xFF6CCFF8)
                                )
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

