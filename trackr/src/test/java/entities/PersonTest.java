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
    public void testPersonHasTagsNonEmpty() {
        this.person.addTag("Cool");
        assertTrue(this.person.hasTag("Cool"));
    }

    @Test
    public void testPersonDoesNotHaveTagsNonEmpty() {
        this.person.addTag("isCool");
        assertFalse(this.person.hasTag("Cool"));
    }

    @Test
    public void testPersonDoesNotHaveTagsEmpty() {
        assertFalse(this.person.hasTag("Cool"));
    }
}
