package com.trackr.trackr_app.ui.add

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trackr.trackr_app.R
import com.trackr.trackr_app.ui.home.HomeScreenViewModel
import com.trackr.trackr_app.ui.main.MainScreen
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.ui.theme.TrackrappTheme




@Composable
fun AddScreenActivity(viewModel: HomeScreenViewModel) {
    val events: List<String> by viewModel.events.observeAsState(listOf())
    AddScreen(onAddItem = {viewModel.addEvent(it)})
}

@Composable
fun AddScreen(
    onAddItem: (String) -> Unit
) {
    var eventName by remember { mutableStateOf("text") }
    Scaffold(
    ) {
        Column(
        ) {
//            OutlinedTextField(
//                value = eventName,
//                onValueChange = {
//                    eventName = it
//                },
//            )
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
