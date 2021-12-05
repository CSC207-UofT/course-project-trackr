package com.trackr.trackr_app.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.trackr.trackr_app.ui.shared.InputWidget
import com.trackr.trackr_app.ui.theme.Rubik
import com.trackr.trackr_app.viewmodels.AddPersonViewModel


@Composable
fun AddPersonScreenActivity(
    viewModel: AddPersonViewModel,
    nav: NavHostController
) {
    val firstName by viewModel.firstName
    val lastName by viewModel.lastName

    Scaffold {
        Column(
            Modifier.padding(20.dp)
        ) {
            InputWidget(
                title = "First Name",
                widgets = listOf {
                    TextField(
                        value = firstName,
                        onValueChange = { viewModel.editFirstName(it) },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            textColor = MaterialTheme.colors.onBackground,
                        ),
                        placeholder = {
                            Text(
                                "First Name", fontFamily = Rubik,

                                )
                        }
                    )
                }
            )
            InputWidget(
                title = "Last Name",
                widgets = listOf {
                    TextField(
                        value = lastName,
                        onValueChange = { viewModel.editLastName(it) },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            textColor = MaterialTheme.colors.onBackground,
                        ),
                        placeholder = { Text("Last Name", fontFamily = Rubik) }
                    )
                }
            )
            Button(
                onClick = {
                    viewModel.addPerson()
                    nav.popBackStack()
                },
                Modifier.padding(top = 20.dp),
            ) {
                Text("Save Person")
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Icon(
                    Icons.Filled.Check,
                    contentDescription = "Create Person",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }
    }
}