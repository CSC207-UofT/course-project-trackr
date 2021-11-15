package com.trackr.trackr_app.manager

import com.trackr.trackr_app.model.Person
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonManager @Inject constructor() {
    fun createPerson(id: String, first_name: String, last_name: String): Person {
        return Person(
            user_id = id,
            first_name = first_name,
            last_name = last_name)
    }
}