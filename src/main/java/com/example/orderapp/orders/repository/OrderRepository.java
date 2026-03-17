package com.example.orderapp.orders.repository;

import com.example.orderapp.orders.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository abstraction for data access.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /** Returns all orders belonging to a customer. */
    List<Order> findByCustomerId(Long customerId);
}
