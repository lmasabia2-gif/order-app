package com.example.orderapp.order.service;

import com.example.orderapp.order.domain.OrderItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class OrderPricingServiceTest {

    private final OrderPricingService service = new OrderPricingService();

    @Test
    void shouldCalculateSubtotalTaxAndShipping() {
        List<OrderItem> items = List.of(
                new OrderItem(UUID.randomUUID(), "SKU-1", "Keyboard", 2, new BigDecimal("49.90"))
        );

        OrderPricingService.PricingResult result = service.calculate(items);

        assertThat(result.subtotal()).isEqualByComparingTo("99.80");
        assertThat(result.tax()).isEqualByComparingTo("21.96");
        assertThat(result.shipping()).isEqualByComparingTo("9.90");
    }
}
