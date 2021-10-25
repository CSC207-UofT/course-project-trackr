package usecases;
import entities.Person;

import java.util.HashSet;

/**
 * PersonManager is a use case class that manages the creation of people
 */
public class PersonManager {

    /**
     * Create a new Person
     * @param firstName - a string representation of a Person object's first name
     * @param lastName - a string representation of a Person object's last name
     * @param description - a string representation of a Person object's description
     * @param tags - any tags/attributes associated with this person
     * @return newly created Person
     */
    public Person createPerson(String firstName, String lastName, String description, HashSet<String> tags) {
        return new Person(firstName, lastName, description, tags);
    }

    /**
     * Create a new Person
     * @param firstName - a string representation of a Person object's first name
     * @param lastName - a string representation of a Person object's last name
     * @param description - a string representation of a Person object's description
     * @return newly created Person
     */
    public Person createPerson(String firstName, String lastName, String description) {
        return new Person(firstName, lastName, description);
    }

    /**
     * Create a new Person
     * @param firstName - a string representation of a Person object's first name
     * @param lastName - a string representation of a Person object's last name
     * @return newly created Person
     */
    public Person createPerson(String firstName, String lastName) {
        return new Person(firstName, lastName);
    }

    /**
     * Create a new Person
     * @param firstName - a string representation of a Person object's first name
     * @return newly created Person
     */
    public Person createPerson(String firstName) {
        return new Person(firstName);
    }

    /**
     * Edit a Person object's first name
     * @param person - a Person object
     * @param newFirstName - a string representation of a Person object's new first name
     */
    public void editFirstName(Person person, String newFirstName) {
        person.setFirstName(newFirstName);
    }

    /**
     * Edit a Person object's last name
     * @param person - a Person object
     * @param newLastName - a string representation of a Person object's new last name
     */
    public void editLastName(Person person, String newLastName){
        person.setLastName(newLastName);
    }

    /**
     * Edit a Person object's description
     * @param person - a Person object
     * @param newDescription - a string representation of a Person object's new description
     */
    public void editDescription(Person person, String newDescription){
        person.setDescription(newDescription);
    }

    /**
     * Add a tag to a Person object
     * @param person - a Person object
     * @param tag - the tag to add to this person
     */
    public void addTags(Person person, String tag){
        person.addTag(tag);
    }

    /**
     * Remove a tag from a Person object
     * @param person - a Person object
     * @param tag - the tag to remove from this person
     */
    public void removeTags(Person person, String tag){
        person.removeTag(tag);
    }


}
