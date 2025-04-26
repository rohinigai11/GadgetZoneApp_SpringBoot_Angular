package com.gadgetzone.service;

import com.gadgetzone.entity.CartItem;
import com.gadgetzone.entity.Order;
import com.gadgetzone.entity.OrderItem;
import com.gadgetzone.entity.User;
import com.gadgetzone.repository.CartItemRepository;
import com.gadgetzone.repository.OrderItemRepository;
import com.gadgetzone.repository.OrderRepository;
import com.gadgetzone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired private UserRepository userRepository;
    @Autowired private CartItemRepository cartItemRepository;
    @Autowired private OrderRepository orderRepository;

    @Override
    public Order createOrder(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOpt.get();

        // Fetch cart items for the user
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty for user id: " + userId);
        }

        BigDecimal totalAmount = BigDecimal.ZERO;

        // Create Order
        Order order = new Order();
        order.setUser(user);
        order.setStatus("PLACED");
        order.setOrderDate(java.time.LocalDateTime.now());

        // Convert cart items into order items
        List<OrderItem> orderItems = new java.util.ArrayList<>();
        for (CartItem cartItem : cartItems) {
            BigDecimal itemTotal = cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);

            OrderItem orderItem = new OrderItem(order, cartItem.getProduct(), cartItem.getQuantity(), cartItem.getProduct().getPrice());
            orderItems.add(orderItem);
        }

        // Set order items and total
        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        // Save order (will cascade save the order items)
        orderRepository.save(order);

        // Clear user's cart
        cartItemRepository.deleteAll(cartItems);

        return order;
    }


    @Override
    public List<Order> getOrdersForUser(Long userId) {
        return orderRepository.findByUser(userRepository.getOne(userId));
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }
}
