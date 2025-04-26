package com.gadgetzone.service;

import com.gadgetzone.entity.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Long userId);  // Method to create an order for a user
    List<Order> getOrdersForUser(Long userId);  // Fetch all orders for a user
    Optional<Order> getOrderById(Long orderId);  // Fetch an order by ID
}
