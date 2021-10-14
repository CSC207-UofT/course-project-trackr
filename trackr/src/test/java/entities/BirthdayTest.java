package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BirthdayTest {
    Birthday birthday;

    @BeforeEach
    void setup() {
        this.birthday = new Birthday(
                new Person("Nopity", "Nope"),
                LocalDate.of(2021, 11, 10),
                LocalDate.of(2021, 11, 5)
                );
    }

    @Test
    void testReminderDatesTrue() {
        assertTrue(this.birthday.isReminderDeadline(LocalDate.of(2021, 11, 5)));
    }

    @Test
    void testReminderDatesFalse() {
        assertFalse(this.birthday.isReminderDeadline(LocalDate.of(2021, 11, 10)));
    }

    @Test
    void testReminderDatesNull() {
        Birthday birthday = new Birthday(
                new Person("Geniveve", "King"),
                LocalDate.of(2021, 11, 10)
                );

        assertFalse(birthday.isReminderDeadline(LocalDate.of(2021, 11, 10)));
    }
}
