package com.trackr.trackr_app.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.trackr.trackr_app.R
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.ui.theme.TrackrappTheme


val temp_data = listOf(1,2,3,4,5)

@Preview(
    uiMode = UI_MODE_NIGHT_YES  // enables dark mode for preview
)
@Composable
private fun PreviewWithTheme() {
    TrackrappTheme {
        HomeScreen(listOf<String>("hello", "hi"))
    }
}

@Composable
fun HomeScreen(
//    homeViewModel: HomeViewModel,
    eventList: List<String>
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        bottomBar = { BottomAppBar() }
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
               listOf()
           )
           HomeFeed(
               Modifier
                   .padding(0.dp, 10.dp)
                   .weight(2f),
               stringResource(R.string.upcoming_events),
               eventList
           )
       }
    }
}

@Composable
fun HomeFeed(modifier: Modifier, title: String, events: List<String>) {
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
            EventList(events)
        }
    }
}

@Composable
fun EventList(events: List<Any>) {
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

@Composable
fun BottomAppBar() {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
    ) {
    }
}
