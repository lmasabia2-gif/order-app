package com.example.orderapp.orders.service;

import com.example.orderapp.orders.api.OrderRequest;
import com.example.orderapp.orders.api.OrderResponse;
import com.example.orderapp.orders.mapper.OrderMapper;
import com.example.orderapp.orders.model.Order;
import com.example.orderapp.orders.repository.OrderRepository;
import com.example.orderapp.shared.exception.ConflictException;
import com.example.orderapp.shared.exception.NotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer responsible for business orchestration.
 */
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional(readOnly = true)
    public OrderResponse findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with id " + id + " not found"));
        return orderMapper.toResponse(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> findByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream().map(orderMapper::toResponse).toList();
    }

    public OrderResponse create(OrderRequest request) {
        Order order = orderMapper.toEntity(request);

        if (order.getAmount() == null || order.getAmount().signum() <= 0) {
            throw new ConflictException("Order amount must be greater than zero");
        }

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }

    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new NotFoundException("Order with id " + id + " not found");
        }
        orderRepository.deleteById(id);
    }
}
