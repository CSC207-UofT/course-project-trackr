package com.trackr.trackr_app.viewmodels

import com.trackr.trackr_app.model.Person

/**
 * A wrapper class used by the UI to display data
 * instead of directly depending on the Person entity
 */
class PersonOutput(person: Person) {
    val personId:String = person.id
    val firstName:String = person.firstName
    val lastName:String = person.lastName
}