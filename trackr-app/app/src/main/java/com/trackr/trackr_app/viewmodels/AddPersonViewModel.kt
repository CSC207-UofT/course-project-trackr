package com.trackr.trackr_app.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trackr.trackr_app.manager.PersonCreator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The view model for AddPersonScreen which manages the data that appears on the AddPersonScreen
 *
 * @param personCreator an object that implements PersonCreator that is used to create new people
 */
@HiltViewModel
class AddPersonViewModel @Inject constructor(
    private val personCreator: PersonCreator,
) : ViewModel() {

    private val _firstName = mutableStateOf("")
    val firstName: State<String> get() = _firstName

    private val _lastName = mutableStateOf("")
    val lastName: State<String> get() = _lastName

    /**
     * Edit the first name of the person to be added.
     *
     * @param newFirstName the new first name of the person
     */
    fun editFirstName(newFirstName: String) = viewModelScope.launch {
        _firstName.value = newFirstName
    }

    /**
     * Edit the last name of the person to be added.
     *
     * @param newLastName the new last name of the person
     */
    fun editLastName(newLastName: String) = viewModelScope.launch {
        _lastName.value = newLastName
    }

    /**
     * Adds a new person to the database according to this.firstName and this.lastName
     * by using personCreator.
     */
    fun addPerson() = viewModelScope.launch {
        personCreator.createPerson(firstName.value, lastName.value)
    }
}