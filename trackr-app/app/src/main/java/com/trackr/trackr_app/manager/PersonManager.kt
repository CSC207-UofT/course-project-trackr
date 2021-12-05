package com.trackr.trackr_app.manager

import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.repository.PersonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonManager @Inject constructor(
        private val personRepository: PersonRepository,
        private val userManager: UserManager
) : PersonCreator, PersonModifier, SinglePersonAccessor {
    override suspend fun createPerson(firstName: String, lastName: String): Person {
        val newPerson = Person(
            userId = userManager.currentUser.id,
            firstName = firstName,
            lastName = lastName)

        personRepository.insert(newPerson)
        return newPerson
    }

    override suspend fun editPerson(id: String, firstName: String, lastName: String) {
        val person = personRepository.getPersonById(id)
        personRepository.editFirstName(firstName, person)
        personRepository.editLastName(lastName, person)

    }

    override suspend fun deletePerson(personID: String) {
        val person = personRepository.getPersonById(personID)
        personRepository.delete(person)
    }

    override suspend fun getPersonById(personId: String) =
        personRepository.getPersonById(personId)
}