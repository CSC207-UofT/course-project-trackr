package com.trackr.trackr_app.manager

import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonManager @Inject constructor(
    private val eventRepository: EventRepository,
    private val personRepository: PersonRepository
) {
    fun createPerson(id: String, first_name: String, last_name: String): Person {
        return Person(
            user_id = id,
            first_name = first_name,
            last_name = last_name)
    }

    suspend fun editPerson(id: String, first_name: String, last_name: String) {
        val person = personRepository.getPersonById(id)
        personRepository.editFirstName(first_name, person)
        personRepository.editLastName(last_name, person)
    }

    suspend fun deletePerson(personID: String) {
        val person = personRepository.getPersonById(personID)
        personRepository.delete(person)
    }
}