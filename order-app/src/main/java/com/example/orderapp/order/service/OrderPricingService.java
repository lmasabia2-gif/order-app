package com.example.orderapp.order.service;

import com.example.orderapp.order.domain.OrderItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderPricingService {

    public PricingResult calculate(List<OrderItem> items) {
        BigDecimal subtotal = items.stream()
                .map(OrderItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal tax = subtotal.multiply(new BigDecimal("0.22"))
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal shipping = subtotal.compareTo(new BigDecimal("100.00")) >= 0
                ? BigDecimal.ZERO
                : new BigDecimal("9.90");

        BigDecimal discount = BigDecimal.ZERO;

        return new PricingResult(subtotal, tax, shipping, discount);
    }

    public record PricingResult(
            BigDecimal subtotal,
            BigDecimal tax,
            BigDecimal shipping,
            BigDecimal discount
    ) {
    }
}
