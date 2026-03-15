package com.example.orderapp.order.api;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        String orderNumber,
        String status,
        UUID customerId,
        BigDecimal subtotalAmount,
        BigDecimal taxAmount,
        BigDecimal shippingAmount,
        BigDecimal discountAmount,
        BigDecimal totalAmount,
        String currency,
        List<OrderItemLine> items,
        Instant createdAt,
        Instant updatedAt
) {
    public record OrderItemLine(
            String productId,
            String productName,
            Integer quantity,
            BigDecimal unitPrice,
            BigDecimal lineTotal
    ) {
    }
}
