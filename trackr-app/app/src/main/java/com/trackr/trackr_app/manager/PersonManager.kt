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
    /**
     * Create a new person with a unique id.
     *
     * @param firstName The first name of the person to add
     * @param lastName The last name of the person to add
     * @return A person object corresponding to the person created
     */
    override suspend fun createPerson(firstName: String, lastName: String): Person {
        val newPerson = Person(
            userId = userManager.currentUser.id,
            firstName = firstName,
            lastName = lastName
        )

        personRepository.insert(newPerson)
        return newPerson
    }

    /**
     * Edit the first and last names of the person.
     *
     * @param id The id of the person object to be modified.
     * @param firstName The modified first name of the person.
     * @param lastName The modified last name of the person.
     */
    override suspend fun editPerson(id: String, firstName: String?, lastName: String?) {
        val person = personRepository.getPersonById(id)
        if (firstName != null) {
            personRepository.editFirstName(firstName, person)
        }
        if (lastName != null) {
            personRepository.editLastName(lastName, person)
        }
    }

    /**
     * Delete a person from the database.
     *
     * @param personID The ID of the person to delete
     */
    override suspend fun deletePerson(personID: String) {
        val person = personRepository.getPersonById(personID)
        personRepository.delete(person)
    }

    /**
     * Get a person from the database.
     *
     * @param personID The ID of the person to retrieve
     * @return The person object corresponding to the ID
     */
    override suspend fun getPersonById(personId: String) =
        personRepository.getPersonById(personId)
}