package com.trackr.trackr_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trackr.trackr_app.ui.main.MainScreen
import com.trackr.trackr_app.ui.theme.TrackrappTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

class MainActivityViewModel: ViewModel() {
    //NOTE: This design is inspired by documentation from Google:
    //https://developer.android.com/codelabs/jetpack-compose-state#3
    private var _events = MutableLiveData(listOf<String>())
    val events: LiveData<List<String>> = _events
    fun addEvent(event: String) {
        _events.value = _events.value!! + listOf(event)
    }
}

class MainActivity : ComponentActivity() {
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TrackrappTheme {
                MainActivityScreen(mainActivityViewModel)
            }
        }
    }
}

@Composable
private fun MainActivityScreen(viewModel: MainActivityViewModel) {
    val events: List<String> by viewModel.events.observeAsState(listOf())
    MainScreen(eventList = events, onAddItem = {viewModel.addEvent(it)})
}