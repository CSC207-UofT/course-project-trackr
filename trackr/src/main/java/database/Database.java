package database;

import entities.Event;
import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private final List<Person> PersonData = new ArrayList<>();
    private final List<Event> EventData = new ArrayList<>();

    public void addPerson(Person person) {
        this.PersonData.add(person);
    }

    public void addEvent(Event event) {
        this.EventData.add(event);
    }

    public boolean removeEvent(Event event) {
        return this.EventData.remove(event);
    }

    public boolean removePerson(Person person) {
        return this.PersonData.remove(person);
    }

    public List<Person> getPersonData() {
        return this.PersonData;
    }

    public List<Event> getEventData() {
        return this.EventData;
    }
}
