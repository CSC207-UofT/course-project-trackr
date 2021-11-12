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
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.ui.home.EventList
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.viewmodels.HomeScreenViewModel
import com.trackr.trackr_app.viewmodels.SelectScreenViewModel
import java.time.Instant
import java.time.ZoneId
import java.util.*


@Composable
fun SelectScreenActivity(viewModel: SelectScreenViewModel, nav: NavHostController) {
    val events: List<TrackrEvent> by viewModel.allEvents.observeAsState(listOf())
    SelectScreen(
        // TODO: figure out how to move this to the viewModel
        eventList = events.map {
            val dateTime = java.time.LocalDateTime.ofInstant(
                Instant.ofEpochSecond(it.date.toLong()), java.time.ZoneId.of(
                    ZoneId.SHORT_IDS.get("EST")))
            listOf(it.id, dateTime.month, dateTime.dayOfMonth, it.reminder_interval)},
        nav = nav)
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
fun SelectFeed(modifier: Modifier,
               title: String,
               events: List<List<Any>>,
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
                Modifier.fillMaxWidth(),
                nav,
            )
        }
    }
}


