package entities;

import java.util.HashSet;
import java.util.Set;

/**
 * A Person who is the target for an Event.
 * The Person class is responsible for storing the first and last name of the person,
 * along with a description and tags associated with this person.
 */
public class Person {
    private String firstName;
    private String lastName;
    private String description;
    private final HashSet<String> tags;

    /**
     * Create a new person that has a first name, last name, description and tags
     * @param firstName the first name of this person
     * @param lastName the last name of this person
     * @param description a description of this person
     * @param tags any tags/attributes associated with this person
     */
    public Person(String firstName, String lastName, String description, HashSet<String> tags) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.tags = tags;
    }

    /**
     * Create a new person that has a first name,last name, and a description
     * @param firstName the first name of this person
     * @param lastName the last name of this person
     * @param description a description of this person
     */
    public Person(String firstName, String lastName, String description) {
        this(firstName, lastName, description, new HashSet<>());
    }


    /**
     * Create a new person that has a first name and a last name
     * @param firstName the first name of this person
     * @param lastName the last name of this person
     */
    public Person(String firstName, String lastName) {
        this(firstName,lastName, "");
    }

    /**
     * Create a new person that has a first name
     * @param firstName the first name of this person
     */
    public Person(String firstName) {
        this(firstName, "");
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name of this Person to firstName
     * @param firstName the new firstName of this Person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Return the lastName of this Person
     * @return the lastName of this Person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name of this Person to lastName
     * @param lastName the new lastName of this Person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Return a description of this person
     * @return the description of this person
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of this Person to description
     * @param description the new description of this Person
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the tags that this Person has
     * @return the tags associated with this Person
     */
    public Set<String> getTags() {
        return tags;
    }

    /**
     * Return whether the person has the given tag
     * @param tag the tag to test for
     * @return a boolean representing whether the person has the given tag or not
     */
    public boolean hasTag(String tag) {
        return this.tags.contains(tag);
    }

    /**
     * Add a tag to this person.
     * Return true if the tag was successfully added and false if this person already has this tag.
     * @param tag the tag to add to this person
     * @return false if the person already has this tag, true otherwise
     */
    public boolean addTag(String tag) {
        return this.tags.add(tag);
    }

    /**
     * Remove the tag from this person.
     * Return true if successfully removed, return false if this person doesn't have that tag
     * @param tag the tag to remove from this person
     * @return false if the entities.Person does not have the tag
     * otherwise return remove the tag and return true
     */
    public boolean removeTag(String tag) {
        return this.tags.remove(tag);
    }
}
