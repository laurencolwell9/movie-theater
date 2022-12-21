package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int guestCount;

    public Reservation(Customer customer, Showing showing, int guestCount) {
        this.customer = customer;
        this.showing = showing;
        this.guestCount = guestCount;
    }

    public double totalFee() {
        return showing.calculateTicketPrice() * guestCount;
    }
}