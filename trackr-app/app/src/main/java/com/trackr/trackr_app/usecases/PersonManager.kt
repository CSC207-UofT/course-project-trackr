package com.trackr.trackr_app.usecases

import com.trackr.trackr_app.entities.Person
import java.util.HashSet

/**
 * PersonManager is a use case class that manages the creation of people
 */
class PersonManager {
    /**
     * Create a new Person
     * @param firstName - a string representation of a Person object's first name
     * @param lastName - a string representation of a Person object's last name
     * @param description - a string representation of a Person object's description
     * @param tags - any tags/attributes associated with this person
     * @return newly created Person
     */
    fun createPerson(
        firstName: String,
        lastName: String = "",
        description: String = "",
        tags: HashSet<String> = HashSet()
    ): Person {
        return Person(firstName, lastName, description, tags)
    }

    /**
     * Create a new Person
     * @param firstName - a string representation of a Person object's first name
     * @param lastName - a string representation of a Person object's last name
     * @param description - a string representation of a Person object's description
     * @return newly created Person
     */
    fun createPerson(firstName: String?, lastName: String?, description: String?): Person {
        return Person(firstName!!, lastName!!, description!!)
    }

    /**
     * Create a new Person
     * @param firstName - a string representation of a Person object's first name
     * @param lastName - a string representation of a Person object's last name
     * @return newly created Person
     */
    fun createPerson(firstName: String?, lastName: String?): Person {
        return Person(firstName!!, lastName!!)
    }

    /**
     * Create a new Person
     * @param firstName - a string representation of a Person object's first name
     * @return newly created Person
     */
    fun createPerson(firstName: String?): Person {
        return Person(firstName!!)
    }

    /**
     * Edit a Person object's first name
     * @param person - a Person object
     * @param newFirstName - a string representation of a Person object's new first name
     */
    fun editFirstName(person: Person, newFirstName: String?) {
        person.firstName = newFirstName!!
    }

    /**
     * Edit a Person object's last name
     * @param person - a Person object
     * @param newLastName - a string representation of a Person object's new last name
     */
    fun editLastName(person: Person, newLastName: String?) {
        person.lastName = newLastName!!
    }

    /**
     * Edit a Person object's description
     * @param person - a Person object
     * @param newDescription - a string representation of a Person object's new description
     */
    fun editDescription(person: Person, newDescription: String?) {
        person.description = newDescription!!
    }

    /**
     * Add a tag to a Person object
     * @param person - a Person object
     * @param tag - the tag to add to this person
     */
    fun addTags(person: Person, tag: String) {
        person.addTag(tag)
    }

    /**
     * Remove a tag from a Person object
     * @param person - a Person object
     * @param tag - the tag to remove from this person
     */
    fun removeTags(person: Person, tag: String) {
        person.removeTag(tag)
    }
}