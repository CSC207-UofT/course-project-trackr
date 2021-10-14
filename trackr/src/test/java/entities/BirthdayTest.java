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
                new Person("Nopity", "Nope"),
                new Date(2021, 11, 10),
                new Date(2021, 11, 5)
                );
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
                new Person("Geniveve", "King"),
                new Date(2021, 11, 10)
                );

        assertFalse(birthday.isReminderDeadline(new Date(2021, 11, 10)));
    }
}
