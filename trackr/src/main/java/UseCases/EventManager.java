package UseCases;

import java.util.HashMap;

/**
 * EventManager is a use case class that manages the creation and deletion of events
 */
public class EventManager {
    //Define a hashmap to store the events
    private HashMap<String, Object> eventMap;

    public EventManager() {
        this.eventMap = new HashMap<>();
    }

    public HashMap<String, Object> getAllEvents() {
        return this.eventMap;
    }

    /**
     * Add an event to the event hash map
     * @param keyName - the key we are adding to the hashmap
     * @param val - the value corresponding to the key
     * @return true if the key is not already in the hashmap and false otherwise
     */
    public boolean addEvent(String keyName, Object val) {
        if (eventMap.containsKey(keyName)) {
            return false;
        }
        this.eventMap.put(keyName, val);
        return true;
    }

    /**
     * Remove an event from the event hash map
     * @param keyName - the key we are adding to the hashmap
     * @return false if the key is not in the hashmap and true if we
     * succesfully remove the key and corresponding value
     */
    public boolean removeEvent(String keyName) {
        if (eventMap.containsKey(keyName)) {
            this.eventMap.remove(keyName);
            return true;
        }
        return false;
    }
}
