package com.trackr.trackr_app.repository

import com.trackr.trackr_app.model.Person
import java.util.*

class PersonManager {
}
fun createPerson(user_id: String, first_name: String, last_name: String): Person {
    return Person(UUID.randomUUID().toString(), user_id, first_name, last_name)
}