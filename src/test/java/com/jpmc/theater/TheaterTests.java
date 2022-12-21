package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TheaterTests {

    /**
     * Test successful reserve and print
     */
    @Test
    void successfulReserveAndPrintTest() {
        List<Showing> schedule = createTestSchedule();
        Theater theater = new Theater(schedule);
        Customer customer = new Customer("John Doe", "id-12345");

        assertDoesNotThrow(() -> theater.reserve(customer, 7, 4));
        theater.printSchedule();
    }

    /**
     * Test unsuccessful reserve
     */
    @Test
    void unsuccessfulReserveTest() {
        List<Showing> schedule = createTestSchedule();
        Theater theater = new Theater(schedule);
        Customer customer = new Customer("John Doe", "id-12345");

        Exception exception = assertThrows(IllegalStateException.class, () -> theater.reserve(customer, 15, 4));
        assertEquals("Not able to find any showing for given sequence 15", exception.getMessage());
    }

    private List<Showing> createTestSchedule() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.50, true);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11.00, false);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9.00, false);
        List<Showing> schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(23, 0)))
        );
        return schedule;
    }
}
