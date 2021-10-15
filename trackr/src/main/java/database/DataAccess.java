package database;

import entities.Event;
import entities.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation for the DatabaseAccessInterface, which preforms Database operations.
 */
public class DataAccess implements DatabaseAccessInterface{
    private final Database database = new Database();

    /**
     * Return a List of Person objects representing all the Person objects in the Database
     * @return a List of all Person objects in the Database
     */
    @Override
    public List<Person> getPersonData() {
        return this.database.getPersonData();
    }

    /**
     * Return a List of all Events in the Database
     * @return a list of all Events in the Database
     */
    @Override
    public List<Event> getEventData() {
        return this.database.getEventData();
    }

    /**
     * Add a new Person to the Database
     * @param person the Person to add to the Database
     */
    @Override
    public void addPerson(Person person) {
        this.database.addPerson(person);
    }


    /**
     * Remove an existing Person from the database. Return a boolean representing if the
     * Person was successfully removed or not.
     * @param person the person to remove from the database
     * @return return a boolean representing if the person was successfully removed or not
     */
    @Override
    public boolean removePerson(Person person) {
        return this.database.removePerson(person);
    }

    /**
     * Add a new Event to the Database
     * @param event the Event to add to the Database
     */
    @Override
    public void addEvent(Event event) {
        this.database.addEvent(event);
    }


    /**
     * Remove an existing Event from the Database. Return a boolean representing
     * whether the Event was successfully removed.
     * @param event the Event to remove
     * @return the boolean indicating if the Event was removed or not.
     */
    @Override
    public boolean removeEvent(Event event) {
        return this.database.removeEvent(event);
    }


    /**
     * Return a List of all Person objects in the database that have
     * the specified first and last names
     * @param firstName the first name of the person to search for
     * @param lastName the last name of the person to search for
     * @return A List of all Person objects
     */
    @Override
    public List<Person> findPerson(String firstName, String lastName) {
        List<Person> personCandidates = new ArrayList<>();
        for (Person person : this.database.getPersonData()) {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                personCandidates.add(person);
            }
        }
        return personCandidates;
    }

    /**
     * Return a List of Events in the Database which have a Person with the
     * specified first and last name.
     * @param firstName the firstName of the Person which the Event is for
     * @param lastName the lastName of the Person for which the Event is for
     * @return A List of Events which have the specified properties
     */
    @Override
    public List<Event> findEvent(String firstName, String lastName) {
        List<Event> eventCandidates = new ArrayList<>();
        for (Event event : this.database.getEventData()) {
            Person eventTarget = event.getPerson();
            if (eventTarget.getFirstName().equals(firstName) && eventTarget.getLastName().equals(lastName)) {
                eventCandidates.add(event);
            }
        }
        return eventCandidates;
    }
}