package com.youtube.ecommerce.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRequest {
    private UserAddress userAddress;

    private List<Cart> cart;

    private Map<String, String> paymentMethod = new HashMap<>();;

    private String deliveryMethod;

    public OrderRequest() {
    }

    public OrderRequest(UserAddress userAddress, List<Cart> cart, Map<String, String> paymentMethod, String deliveryMethod) {
        this.userAddress = userAddress;
        this.cart = cart;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public Map<String, String> getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Map<String, String> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
}
