package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

public class Movie {

    private String title;
    private Duration runningTime;
    private double ticketPrice;
    private boolean specialMovie;

    public Movie(String title, Duration runningTime, double ticketPrice, boolean specialMovie) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialMovie = specialMovie;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public boolean isSpecialMovie() {
        return specialMovie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialMovie, movie.specialMovie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, specialMovie);
    }
}