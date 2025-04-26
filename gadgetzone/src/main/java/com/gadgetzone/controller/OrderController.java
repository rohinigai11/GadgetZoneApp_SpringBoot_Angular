package com.gadgetzone.controller;

import com.gadgetzone.entity.Order;
import com.gadgetzone.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired private OrderService orderService;

    // Place an order for a user (converts cart → order)
    @PostMapping("/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long userId) {
        Order order = orderService.createOrder(userId);
        return ResponseEntity.ok(order);
    }

    // List all orders belonging to a user
    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderService.getOrdersForUser(userId);
    }

    // Get single order by its ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }
}
