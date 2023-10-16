package com.example.kafkatest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingPayload implements Serializable {
    private int bookingId;
    private int amount;
}
