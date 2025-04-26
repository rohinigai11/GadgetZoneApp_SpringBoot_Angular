package com.gadgetzone.service;

import com.gadgetzone.entity.CartItem;
import java.util.List;

public interface CartService {
    CartItem addToCart(Long userId, Long productId, int quantity);  // Add product to the user's cart
    List<CartItem> getCartItems(Long userId);  // Get all cart items for a user
    void removeCartItem(Long cartItemId);  // Remove a specific cart item
    void clearCart(Long userId);  // Clear the entire cart for a user
}
