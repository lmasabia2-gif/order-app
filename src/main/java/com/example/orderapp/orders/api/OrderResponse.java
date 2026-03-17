package com.example.orderapp.orders.api;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Response DTO returned to API consumers.
 */
public class OrderResponse {

    private Long id;
    private Long customerId;
    private BigDecimal amount;
    private String status;
    private Instant createdAt;

    public OrderResponse() {
    }

    public OrderResponse(Long id, Long customerId, BigDecimal amount, String status, Instant createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public Long getCustomerId() { return customerId; }
    public BigDecimal getAmount() { return amount; }
    public String getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setStatus(String status) { this.status = status; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
