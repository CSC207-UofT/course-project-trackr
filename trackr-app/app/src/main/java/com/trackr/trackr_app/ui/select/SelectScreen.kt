package com.trackr.trackr_app.ui.select

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import com.trackr.trackr_app.ui.home.EventList
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.viewmodels.SelectScreenViewModel
import com.trackr.trackr_app.viewmodels.TrackrEventOutput


@Composable
fun SelectScreenActivity(viewModel: SelectScreenViewModel, nav: NavHostController) {
    val eventList by viewModel.allEvents.observeAsState(listOf())
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
               events: List<TrackrEventOutput>,
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


