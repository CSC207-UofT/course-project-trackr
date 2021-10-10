package entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person person;

    @BeforeEach
    void setUp() {
        this.person = new Person("David");
    }

    @Test
    public void testAddTagSuccess() {
        assertTrue(this.person.addTag("Cool"));
    }

    @Test
    public void testAddExitingTag() {
        this.person.addTag("Cool");
        assertFalse(this.person.addTag("Cool"));
    }

    @Test
    public void testRemoveExistingTag() {
        this.person.addTag("Cool");
        assertTrue(this.person.removeTag("Cool"));
    }

    @Test
    public void testRemoveNonexistentTag() {
        assertFalse(this.person.removeTag("Cool"));
    }

    @Test
    public void testPersonHasTags() {
        this.person.addTag("Cool");
        assertTrue(this.person.hasTags());
    }

    @Test
    public void testPersonDoesNotHaveTags() {
        assertFalse(this.person.hasTags());
    }
}
