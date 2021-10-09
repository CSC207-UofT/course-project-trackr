package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AnniversaryTest {

    @Test
    void testReminderDatesTrue() {
        Anniversary anniversary = new Anniversary(
                "Wedding",
                new Date(2021, 11, 10),
                new Date(2021, 11, 5),
                new Person());

        assertTrue(anniversary.isReminderDeadline(new Date(2021, 11, 5)));
    }

    @Test
    void testReminderDatesFalse() {
        Anniversary anniversary = new Anniversary(
                "Wedding",
                new Date(2021, 11, 10),
                new Date(2021, 11, 5),
                new Person());

        assertTrue(anniversary.isReminderDeadline(new Date(2021, 11, 10)));
    }

    @Test
    void testReminderDatesNull() {
        Anniversary anniversary = new Anniversary(
                "Wedding",
                new Date(2021, 11, 10),
                new Person());

        assertTrue(anniversary.isReminderDeadline(new Date(2021, 11, 10)));
    }
}