package com.trackr.trackr_app.manager

import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.repository.EventRepository
import com.trackr.trackr_app.repository.PersonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonManager @Inject constructor(
        private val eventRepository: EventRepository,
        private val personRepository: PersonRepository,
        private val userManager: UserManager
) {
    private suspend fun createPerson(userId: String, firstName: String, lastName: String): Person {
        val newPerson = Person(
            userId = userId,
            firstName = firstName,
            lastName = lastName)

        personRepository.insert(newPerson)
        return newPerson
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

    /**
     * Retrieve a person from the database.
     * If the person with the required first and last
     * name does not exist in the database, create and add
     * them and return the object.
     *
     * @param firstName the first name of the wanted person
     * @param lastName the last name of the wanted person
     * @return the person object corresponding to this first and last name
     */
    suspend fun materializePerson(firstName: String, lastName: String): Person {
        // TODO: Can I query the database with a get method only once, and just
        //  Replace this with a null check instead?
        //  I'm not sure what a failed query would do.
        if (personRepository.hasPersonByName(firstName, lastName)) {
            return personRepository.getPersonByName(firstName, lastName)
        } else {
            return createPerson(userManager.currentUser.id, firstName, lastName)
        }
    }

    suspend fun getPersonById(personId: String) =
        personRepository.getPersonById(personId)

}