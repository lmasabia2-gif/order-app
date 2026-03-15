package com.example.orderapp.order.service;

import com.example.orderapp.customer.domain.Customer;
import com.example.orderapp.customer.repository.CustomerRepository;
import com.example.orderapp.order.api.CreateOrderRequest;
import com.example.orderapp.order.api.OrderItemRequest;
import com.example.orderapp.order.api.OrderResponse;
import com.example.orderapp.order.mapper.OrderMapper;
import com.example.orderapp.order.repository.OrderRepository;
import com.example.orderapp.order.repository.OrderStatusHistoryRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private OrderRepository orderRepository;
    private OrderStatusHistoryRepository historyRepository;
    private CustomerRepository customerRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        historyRepository = Mockito.mock(OrderStatusHistoryRepository.class);
        customerRepository = Mockito.mock(CustomerRepository.class);

        orderService = new OrderService(
                orderRepository,
                historyRepository,
                customerRepository,
                new OrderNumberGenerator(),
                new OrderPricingService(),
                new OrderStateMachine(),
                new OrderMapper()
        );
    }

    @Test
    void shouldCreateOrder() {
        UUID customerId = UUID.randomUUID();
        Customer customer = Mockito.mock(Customer.class);

        when(customer.getId()).thenReturn(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(orderRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        CreateOrderRequest request = new CreateOrderRequest(
                customerId,
                "EUR",
                List.of(new OrderItemRequest("SKU-1", "Keyboard", 1, new BigDecimal("49.90")))
        );

        OrderResponse response = orderService.createOrder(request);

        assertThat(response.customerId()).isEqualTo(customerId);
        assertThat(response.currency()).isEqualTo("EUR");
        assertThat(response.items()).hasSize(1);
    }
}
