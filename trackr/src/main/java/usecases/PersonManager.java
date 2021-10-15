package usecases;
import entities.Person;

/**
 * PersonManager is a use case class that manages the creation of people
 */
public class PersonManager {
    /**
     * Create a new Person
     * @param firstName - a string representation of a Person object's first name
     * @param lastName - a string representation of a Person object's last name
     * @return newly created Person
     */
    public Person createPerson(String firstName, String lastName) {
        return new Person(firstName, lastName);
    }
}
