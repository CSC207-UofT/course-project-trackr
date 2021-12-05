package com.trackr.trackr_app.manager

import com.trackr.trackr_app.model.Person

/*
An interface depended on by any view model that needs to add new persons to the database.
Any Manager class that creates and adds persons to the database should implement this interface.
 */
interface PersonCreator {
    suspend fun createPerson(firstName: String, lastName: String): Person
}