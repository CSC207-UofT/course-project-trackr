package events;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BirthdayTest {

    @Test
    void testReminderDatesTrue() {
        Birthday birthday = new Birthday(
                "Wedding",
                new Date(2021, 11, 10),
                new Date(2021, 11, 5),
                new Person());

        assertTrue(birthday.isReminderDeadline(new Date(2021, 11, 5)));
    }

    @Test
    void testReminderDatesFalse() {
        Birthday birthday = new Birthday(
                "Wedding",
                new Date(2021, 11, 10),
                new Date(2021, 11, 5),
                new Person());

        assertTrue(birthday.isReminderDeadline(new Date(2021, 11, 10)));
    }

    @Test
    void testReminderDatesNull() {
        Birthday birthday = new Birthday(
                "Wedding",
                new Date(2021, 11, 10),
                new Person());

        assertTrue(birthday.isReminderDeadline(new Date(2021, 11, 10)));
    }
}
