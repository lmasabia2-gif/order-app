package com.example.orderapp.order.domain;

public enum OrderStatus {
    CREATED,
    PENDING_INVENTORY,
    INVENTORY_RESERVED,
    INVENTORY_REJECTED,
    PENDING_PAYMENT,
    PAYMENT_AUTHORIZED,
    PAYMENT_FAILED,
    READY_TO_SHIP,
    SHIPPED,
    COMPLETED,
    CANCELLED
}
