package com.trackr.trackr_app.repository

import com.trackr.trackr_app.model.Person

interface PersonAccessor {
    suspend fun getPersonById(id: String): Person
    suspend fun getPersonByName(firstName: String, lastName: String): Person
    suspend fun hasPersonByName(firstName: String, lastName: String): Boolean
}