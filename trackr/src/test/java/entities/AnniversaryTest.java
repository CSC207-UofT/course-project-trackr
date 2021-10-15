package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AnniversaryTest {

    Anniversary anniversary;

    @BeforeEach
    void setup() {
        this.anniversary = new Anniversary(
                new Person("Nopity", "Nope"),
                LocalDate.of(2021, 11, 1),
                LocalDate.of(2021, 11, 5)
                );
    }

    @Test
    void testReminderDatesTrue() {
        assertTrue(this.anniversary.isReminderDeadline(LocalDate.of(2021, 11, 5)));
    }

    @Test
    void testReminderDatesFalse() {
        assertFalse(this.anniversary.isReminderDeadline(LocalDate.of(2021, 11, 10)));
    }

    @Test
    void testReminderDatesNull() {
        Anniversary anniversary = new Anniversary(
                new Person("Goodbye"),
                LocalDate.of(2021, 11, 10)
                );

        assertFalse(anniversary.isReminderDeadline(LocalDate.of(2021, 11, 10)));
    }
}