package com.trackr.trackr_app.repository

import com.trackr.trackr_app.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonAccessor {
    fun getAllPersons(): Flow<List<Person>>
    suspend fun getPersonById(id: String): Person
    suspend fun getPersonByName(firstName: String, lastName: String): Person
    suspend fun hasPersonByName(firstName: String, lastName: String): Boolean
}