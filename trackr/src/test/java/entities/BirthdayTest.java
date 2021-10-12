package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BirthdayTest {
    Birthday birthday;

    @BeforeEach
    void setup() {
        this.birthday = new Birthday(
                "Wedding",
                new Date(2021, 11, 10),
                new Date(2021, 11, 5),
                new Person("Nopity", "Nope"));
    }

    @Test
    void testReminderDatesTrue() {
        assertTrue(this.birthday.isReminderDeadline(new Date(2021, 11, 5)));
    }

    @Test
    void testReminderDatesFalse() {
        assertFalse(this.birthday.isReminderDeadline(new Date(2021, 11, 10)));
    }

    @Test
    void testReminderDatesNull() {
        Birthday birthday = new Birthday(
                "Wedding",
                new Date(2021, 11, 10),
                new Person("Geniveve", "King"));

        assertFalse(birthday.isReminderDeadline(new Date(2021, 11, 10)));
    }
}
