package com.trackr.trackr_app.ui.navigation

import androidx.compose.runtime.Immutable

@Immutable
sealed class NavScreen(val route: String) {
    object Home: NavScreen("Home")
    object Add: NavScreen("Home")
    object Edit: NavScreen("Edit")
    object Calendar: NavScreen("Calendar")
}