package com.youtube.ecommerce.controller;

import com.youtube.ecommerce.entity.Cart;
import com.youtube.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/addToCart/{productId}")

    public Cart addToCart(
        @PathVariable("productId") Integer productId
    ) {
        return cartService.addToCart(productId);
    }

    @GetMapping("/getCartItems")
    public List<Cart> getCartItems() {
        return cartService.getCartItems();
    }

    @DeleteMapping("/deleteCartItem/{cartId}")
    public void deleteCartItem(
        @PathVariable("cartId") Integer cartId
    ) {
        cartService.removeCartById(cartId);
    }
}
