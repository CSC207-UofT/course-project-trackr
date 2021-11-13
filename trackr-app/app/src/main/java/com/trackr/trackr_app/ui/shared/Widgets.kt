package com.trackr.trackr_app.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.ui.theme.allGradients
import com.trackr.trackr_app.viewmodels.TrackrEventOutput
import java.time.format.TextStyle
import java.util.*

@Composable
fun InputWidget(
    title: String,
    widgets: List<@Composable() () -> Unit>,
    modifier: Modifier = Modifier
) {
    Column() {
        Text(text = title, Modifier.padding(bottom = 5.dp), fontWeight = FontWeight.Bold)
        Row() {
            for (widget in widgets) {
                Box(
                    modifier
                        .padding(bottom = 20.dp, end = 5.dp)
                        .border(
                            width = 3.dp,
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF3E69FF),
                                    Color(0xFF6CCFF8)
                                )
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )){
                    widget()
                }
            }
        }
    }
}

@Composable
fun InputWidget(title: String, widget: @Composable() () -> Unit) {
    InputWidget(title = title, widgets = listOf(widget))
}

@Composable
fun <T>InteractiveDropdownWidget(setter: (T) -> Unit, getter: () -> T, options: List<T>) {
    var expanded by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(getter().toString(), Modifier.padding(start = 15.dp))
        IconButton(onClick = { expanded = true }) {
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = "Localized description"
            )
        }
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        for (option in options) {
            DropdownMenuItem(onClick = {
                setter(option); expanded = false
            }) {
                Text(option.toString())
            }
        }
    }
}

@Composable
fun EventCard(navController: NavHostController, index: Int, event: TrackrEventOutput) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("Edit/${event.id}")
            },
        shape = RoundedCornerShape(20),
    ) {
        Row(
            Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = allGradients[index % 3]
                    )
                )
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("${event.firstName} ${event.lastName}'s ",
                    fontFamily = Rubik,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    event.date.month.getDisplayName(TextStyle.FULL, Locale.getDefault()) +
                            " ${event.date.dayOfMonth}",
                    color = Color.White,
                    fontSize = 13.sp
                )
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    event.eventAge.toString() +
                            if (event.eventAge.toString().endsWith("11") ||
                                event.eventAge.toString().endsWith("12") ||
                                event.eventAge.toString().endsWith("13"))
                                "th"
                            else if (event.eventAge.toString().endsWith("1"))
                                "st"
                            else if (event.eventAge.toString().endsWith("2"))
                                "nd"
                            else if (event.eventAge.toString().endsWith("3"))
                                "rd"
                            else "th",
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp
                )
                Text(if (event.type == 0) "Birthday" else "Anniversary",
                    fontFamily = Rubik,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
            }
        }
    }
}
