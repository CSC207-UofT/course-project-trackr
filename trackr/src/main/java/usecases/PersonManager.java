package usecases;
import database.DatabaseAccessInterface;
import entities.Event;
import entities.Person;

import java.util.List;

/**
 * PersonManager is a use case class that manages the creation of people
 */
public class PersonManager {
    private DatabaseAccessInterface dataAccessor;

    public PersonManager(DatabaseAccessInterface dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    /**
     * Create a new Person
     * @param firstName - a string representation of a Person object's first name
     * @param lastName - a string representation of a Person object's last name
     * @return newly created Person
     */
    public Person createPerson(String firstName, String lastName) {
        Person person = new Person(firstName, lastName);
        return person;
    }

    /**
     * Find a Person from the database
     * @param firstName - a string representation of a Person object's first name
     * @param lastName - a string representation of a Person object's last name
     * @return all events that ocrrespond to the specificed person
     */
    public List<Event> getPerson(String firstName, String lastName) {
        List<Event> events = this.dataAccessor.findEvent(firstName, lastName);
        return events;
    }

}
