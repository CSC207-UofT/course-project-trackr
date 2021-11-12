package com.trackr.trackr_app.ui.shared

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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

@Composable
fun InputWidget(title: String, widgets: List<@Composable() () -> Unit>) {
    Column() {
        Text(text = title, Modifier.padding(bottom = 5.dp), fontWeight = FontWeight.Bold)
        Row() {
            for (widget in widgets) {
                Box(
                    Modifier
                        .padding(bottom = 20.dp, end = 5.dp)
                        .border(
                            width = 3.dp, brush = Brush.horizontalGradient(
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
                setter(option)
                expanded = false
            }
            ) {
                Text(option.toString())
            }
        }
    }
}
