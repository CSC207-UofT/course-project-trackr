package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AnniversaryTest {

    Anniversary anniversary;

    @BeforeEach
    void setup() {
        this.anniversary = new Anniversary(
                new Person("Nopity", "Nope"),
                new Date(2021, 11, 10),
                new Date(2021, 11, 5)
                );
    }

    @Test
    void testReminderDatesTrue() {
        assertTrue(this.anniversary.isReminderDeadline(new Date(2021, 11, 5)));
    }

    @Test
    void testReminderDatesFalse() {
        assertFalse(this.anniversary.isReminderDeadline(new Date(2021, 11, 10)));
    }

    @Test
    void testReminderDatesNull() {
        Anniversary anniversary = new Anniversary(
                new Person("Goodbye"),
                new Date(2021, 11, 10)
                );

        assertFalse(anniversary.isReminderDeadline(new Date(2021, 11, 10)));
    }
}