package com.trackr.trackr_app.manager

import com.trackr.trackr_app.model.Person

/*
An interface depended on by any view model that needs to access a single person using a property
such as the person id.
Any Manager class that can access a specific person in a database should implement this interface.
 */
interface SinglePersonAccessor {
    suspend fun getPersonById(personId: String): Person
}