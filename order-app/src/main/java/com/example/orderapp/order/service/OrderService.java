package com.example.orderapp.order.service;

import com.example.orderapp.common.exception.NotFoundException;
import com.example.orderapp.customer.domain.Customer;
import com.example.orderapp.customer.repository.CustomerRepository;
import com.example.orderapp.order.api.CancelOrderRequest;
import com.example.orderapp.order.api.CreateOrderRequest;
import com.example.orderapp.order.api.OrderResponse;
import com.example.orderapp.order.domain.Order;
import com.example.orderapp.order.domain.OrderItem;
import com.example.orderapp.order.domain.OrderStatus;
import com.example.orderapp.order.domain.OrderStatusHistory;
import com.example.orderapp.order.mapper.OrderMapper;
import com.example.orderapp.order.repository.OrderRepository;
import com.example.orderapp.order.repository.OrderStatusHistoryRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusHistoryRepository historyRepository;
    private final CustomerRepository customerRepository;
    private final OrderNumberGenerator orderNumberGenerator;
    private final OrderPricingService pricingService;
    private final OrderStateMachine stateMachine;
    private final OrderMapper orderMapper;

    public OrderService(
            OrderRepository orderRepository,
            OrderStatusHistoryRepository historyRepository,
            CustomerRepository customerRepository,
            OrderNumberGenerator orderNumberGenerator,
            OrderPricingService pricingService,
            OrderStateMachine stateMachine,
            OrderMapper orderMapper
    ) {
        this.orderRepository = orderRepository;
        this.historyRepository = historyRepository;
        this.customerRepository = customerRepository;
        this.orderNumberGenerator = orderNumberGenerator;
        this.pricingService = pricingService;
        this.stateMachine = stateMachine;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new NotFoundException("Customer not found: " + request.customerId()));

        Order order = new Order(UUID.randomUUID(), orderNumberGenerator.next(), customer.getId(), request.currency());

        request.items().forEach(item -> order.addItem(
                new OrderItem(UUID.randomUUID(), item.productId(), item.productName(), item.quantity(), item.unitPrice())
        ));

        OrderPricingService.PricingResult pricing = pricingService.calculate(order.getItems());
        order.applyTotals(pricing.subtotal(), pricing.tax(), pricing.shipping(), pricing.discount());
        order.changeStatus(OrderStatus.CREATED);

        Order saved = orderRepository.save(order);
        historyRepository.save(new OrderStatusHistory(saved.getId(), null, OrderStatus.CREATED, "Order created"));

        return orderMapper.toResponse(saved);
    }

    @Transactional
    public OrderResponse cancelOrder(UUID orderId, CancelOrderRequest request) {
        Order order = orderRepository.findWithItemsById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found: " + orderId));

        OrderStatus oldStatus = order.getStatus();
        stateMachine.cancel(order);
        historyRepository.save(new OrderStatusHistory(order.getId(), oldStatus, order.getStatus(), request.reason()));

        return orderMapper.toResponse(order);
    }

    public OrderResponse getOrder(UUID orderId) {
        Order order = orderRepository.findWithItemsById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found: " + orderId));

        return orderMapper.toResponse(order);
    }
}
