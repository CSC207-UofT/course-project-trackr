import java.util.HashSet;

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


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Add a tag to this person.
     * Return true if the tag was successfully added and false if this person already has this tag.
     * @param tag the tag to add to this person
     * @return false if the person already has this tag, true otherwise
     */
    public boolean addTag(String tag) {
        return tags.add(tag);
    }

    /**
     * Remove the tag from this person.
     * Return true if successfully removed, return false if this person doesn't have that tag
     * @param tag the tag to remove from this person
     * @return false if the Person does not have the tag
     * otherwise return remove the tag and return true
     */
    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }
}
