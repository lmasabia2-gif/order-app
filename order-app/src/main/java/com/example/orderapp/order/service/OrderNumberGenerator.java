package com.example.orderapp.order.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class OrderNumberGenerator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.BASIC_ISO_DATE;
    private final AtomicLong sequence = new AtomicLong(1);

    public String next() {
        return "ORD-" + LocalDate.now().format(FORMATTER) + "-" + String.format("%06d", sequence.getAndIncrement());
    }
}
