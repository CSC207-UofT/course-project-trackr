package com.trackr.trackr_app.manager

/*
An interface depended on by any view model that needs to modify existing persons in the database.
Any Manager class that can edit or delete persons in the database should implement on this interface.
 */
interface PersonModifier {
    suspend fun editPerson(id: String, firstName: String, lastName: String)
    suspend fun deletePerson(personID: String)
}