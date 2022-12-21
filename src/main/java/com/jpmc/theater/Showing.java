package com.jpmc.theater;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public double calculateTicketPrice() {
        return movie.getTicketPrice() - getDiscount();
    }

    private double getDiscount() {
        double specialDiscount = 0;
        if (movie.isSpecialMovie()) {
            specialDiscount = movie.getTicketPrice() * 0.20;  // 20% discount for special movie
        }

        double sequenceDiscount = 0;
        if (sequenceOfTheDay == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (sequenceOfTheDay == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        }

        double matineeDiscount = 0;
        if (showStartTime.getHour() >= 11 && showStartTime.getHour() <= 16) {
            matineeDiscount = movie.getTicketPrice() * 0.25; // 25% discount for early movie
        }

        double dateDiscount = 0;
        if (showStartTime.getDayOfMonth() == 7) {
            dateDiscount = 1; // $1 discount for show on 7th of month
        }

        // biggest discount wins
        return Collections.max(Arrays.asList(specialDiscount, sequenceDiscount, matineeDiscount, dateDiscount));
    }
}
