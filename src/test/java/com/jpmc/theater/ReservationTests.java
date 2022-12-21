package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTests {

    /**
     * Test totalFee function without any discounts (12.5 * 3 = 37.5)
     */
    @Test
    void totalFeeTest() {
        var customer = new Customer("John Doe", "id-12345");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.50, false),
                5,
                LocalDateTime.of(LocalDate.of(2022, 12, 21), LocalTime.of(19, 0))
        );
        assertEquals(37.50, new Reservation(customer, showing, 3).totalFee());
    }
}
