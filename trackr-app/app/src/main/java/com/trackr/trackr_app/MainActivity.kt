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



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TrackrappTheme {
                MainScreen()
            }
        }
    }
}