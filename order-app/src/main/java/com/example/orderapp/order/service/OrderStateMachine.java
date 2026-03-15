package com.example.orderapp.order.service;

import com.example.orderapp.common.exception.ConflictException;
import com.example.orderapp.order.domain.Order;
import com.example.orderapp.order.domain.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderStateMachine {

    public void cancel(Order order) {
        if (order.getStatus() == OrderStatus.SHIPPED || order.getStatus() == OrderStatus.COMPLETED) {
            throw new ConflictException("Order cannot be cancelled after shipment or completion");
        }
        order.changeStatus(OrderStatus.CANCELLED);
    }
}
