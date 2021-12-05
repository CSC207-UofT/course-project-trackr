package com.trackr.trackr_app.manager
/*
An interface depended on by any view model that needs to access a single event using a property such
as name or person.
Any Manager class that can access a specific person in a database should implement this interface.
 */
interface SingleEventAccessorInterface {
    suspend fun getEventInfo(eventID: String): List<Any>
}