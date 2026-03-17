package com.example.orderapp.orders.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Request DTO used when a client creates a new order.
 */
public class OrderRequest {

    @NotNull(message = "customerId is required")
    private Long customerId;

    @NotNull(message = "amount is required")
    @DecimalMin(value = "0.01", message = "amount must be greater than 0")
    private BigDecimal amount;

    public OrderRequest() {
    }

    public OrderRequest(Long customerId, BigDecimal amount) {
        this.customerId = customerId;
        this.amount = amount;
    }

    public Long getCustomerId() { return customerId; }
    public BigDecimal getAmount() { return amount; }

    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
