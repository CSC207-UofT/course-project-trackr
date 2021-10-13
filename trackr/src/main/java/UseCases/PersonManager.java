package usecases;
import entities.Person;

import java.util.HashMap;

public class PersonManager {
    private HashMap<String, Object> eventMap;

    public PersonManager() {
        this.eventMap = new HashMap<>();
    }

    public boolean addPerson(String name, Person value){
        if (this.eventMap.containsKey(name)){
            return false;
        } else {
            this.eventMap.put(name, value);
            return true;
        }
    }

    public boolean removePerson(String name){
        if (!this.eventMap.containsKey(name)){
            return false;
        } else {
            this.eventMap.remove(name);
            return true;
        }
    }

    public boolean editPerson(String name, Person newValue){
        if (!this.eventMap.containsKey(name)){
            return false;
        } else {
            this.eventMap.put(name, newValue);
            return true;
        }
    }
}
