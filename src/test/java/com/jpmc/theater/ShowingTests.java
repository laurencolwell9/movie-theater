package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShowingTests {
    /**
     * Test calculateTicketPrice function for special movie 20% discount (10 - 2 = 8)
     */
    @Test
    void specialMovieDiscountTest() {
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, true),
                5,
                LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(19, 0))
        );
        assertEquals(8, showing.calculateTicketPrice());
    }

    /**
     * Test calculateTicketPrice function for first showing $3 discount (10 - 3 = 7)
     */
    @Test
    void firstShowingDiscountTest() {
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, false),
                1,
                LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(19, 0))
        );
        assertEquals(7, showing.calculateTicketPrice());
    }

    /**
     * Test calculateTicketPrice function for second showing $2 discount (10 - 2 = 8)
     */
    @Test
    void secondShowingDiscountTest() {
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, false),
                2,
                LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(19, 0))
        );
        assertEquals(8, showing.calculateTicketPrice());
    }

    /**
     * Test calculateTicketPrice function for matinee (starting 11am - 4pm) 25% discount (10 - 2.5 = 7.5)
     */
    @Test
    void matineeDiscountTest() {
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, false),
                5,
                LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(12, 0))
        );
        assertEquals(7.5, showing.calculateTicketPrice());
    }

    /**
     * Test calculateTicketPrice function for showing on 7th $1 discount (10 - 1 = 9)
     */
    @Test
    void seventhDateDiscountTest() {
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, false),
                5,
                LocalDateTime.of(LocalDate.of(2022, 12, 7), LocalTime.of(19, 0))
        );
        assertEquals(9, showing.calculateTicketPrice());
    }

    /**
     * Test calculateTicketPrice function for multiple discounts
     * (special movie, first showing, seventh, matinee)
     * (max(2, 3, 1, 2.5) = 3)
     */
    @Test
    void multipleDiscountsTest() {
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, true),
                1,
                LocalDateTime.of(LocalDate.of(2022, 12, 7), LocalTime.of(11, 0))
        );
        assertEquals(7, showing.calculateTicketPrice());
    }
}
