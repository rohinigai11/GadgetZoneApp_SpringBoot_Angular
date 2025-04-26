package com.gadgetzone.repository;

import com.gadgetzone.entity.CartItem;
import com.gadgetzone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Custom method to find cart items by user
    List<CartItem> findByUser(User user);  // Method to find CartItems for a specific User
}
