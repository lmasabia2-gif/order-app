package com.example.orderapp.orders.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * JPA entity representing an order stored in the database.
 */
@Entity
@Table(name = "orders")
public class Order {

    /** Database-generated primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Business identifier of the customer. */
    @Column(nullable = false)
    private Long customerId;

    /** Total amount of the order. */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    /** Current order status. */
    @Column(nullable = false, length = 30)
    private String status;

    /** Creation timestamp. */
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    public Order() {
    }

    public Order(Long id, Long customerId, BigDecimal amount, String status, Instant createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    /** Initialize technical fields before insert. */
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (status == null || status.isBlank()) {
            status = "CREATED";
        }
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
