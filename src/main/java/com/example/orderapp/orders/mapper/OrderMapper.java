package com.example.orderapp.orders.mapper;

import com.example.orderapp.orders.api.OrderRequest;
import com.example.orderapp.orders.api.OrderResponse;
import com.example.orderapp.orders.model.Order;
import org.springframework.stereotype.Component;

/**
 * Mapper that converts between API DTOs and persistence entities.
 */
@Component
public class OrderMapper {

    public Order toEntity(OrderRequest request) {
        if (request == null) {
            return null;
        }

        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setAmount(request.getAmount());
        order.setStatus("CREATED");
        return order;
    }

    public OrderResponse toResponse(Order order) {
        if (order == null) {
            return null;
        }

        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }
}
