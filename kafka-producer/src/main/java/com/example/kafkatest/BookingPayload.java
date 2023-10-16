package com.example.kafkatest;

import java.io.Serializable;


public class BookingPayload implements Serializable {
    private int bookingId;
    private int amount;

    public BookingPayload(int bookingId, int amount) {
        this.bookingId = bookingId;
        this.amount = amount;
    }

    public BookingPayload setBookingId(int bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public BookingPayload setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getAmount() {
        return amount;
    }
}
