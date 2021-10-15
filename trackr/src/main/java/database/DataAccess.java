package database;

import entities.Event;
import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class DataAccess implements DatabaseAccessInterface{
    private final Database database = new Database();

    @Override
    public List<Person> getPersonData() {
        return this.database.getPersonData();
    }

    @Override
    public List<Event> getEventData() {
        return this.database.getEventData();
    }

    @Override
    public void addPerson(Person person) {
        this.database.addPerson(person);
    }

    @Override
    public boolean removePerson(Person person) {
        return this.database.removePerson(person);
    }

    @Override
    public void addEvent(Event event) {
        this.database.addEvent(event);
    }

    @Override
    public boolean removeEvent(Event event) {
        return this.database.removeEvent(event);
    }

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