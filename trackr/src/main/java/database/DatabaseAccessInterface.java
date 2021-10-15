package database;

import entities.Event;
import entities.Person;

import java.time.LocalDate;
import java.util.List;

/**
 * An interface which preforms database operations and allows other classes to
 * operate on the database through its methods.
 */
public interface DatabaseAccessInterface {
    /**
     * Return a List of Person objects representing all the Person objects in the Database
     * @return a List of all Person objects in the Database
     */
    List<Person> getPersonData();

    /**
     * Return a List of all Events in the Database
     * @return a list of all Events in the Database
     */
    List<Event> getEventData();

    /**
     * Add a new Person to the Database
     * @param person the Person to add to the Database
     */
    void addPerson(Person person);

    /**
     * Remove an existing Person from the database. Return a boolean representing if the
     * Person was successfully removed or not.
     * @param person the person to remove from the database
     * @return return a boolean representing if the person was successfully removed or not
     */
    boolean removePerson(Person person);

    /**
     * Add a new Event to the Database
     * @param event the Event to add to the Database
     */
    void addEvent(Event event);

    /**
     * Remove an existing Event from the Database. Return a boolean representing
     * whether the Event was successfully removed.
     * @param event the Event to remove
     * @return the boolean indicating if the Event was removed or not.
     */
    boolean removeEvent(Event event);

    /**
     * Return a List of all Person objects in the database that have
     * the specified first and last names
     * @param firstName the first name of the person to search for
     * @param lastName the last name of the person to search for
     * @return A List of all Person objects
     */
    List<Person> findPerson(String firstName, String lastName);

    /**
     * Return a List of Events in the Database which have a Person with the
     * specified first and last name.
     * @param firstName the firstName of the Person which the Event is for
     * @param lastName the lastName of the Person for which the Event is for
     * @return A List of Events which have the specified properties
     */
    List<Event> findEvent(String firstName, String lastName);
}
