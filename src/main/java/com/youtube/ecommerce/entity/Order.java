package com.youtube.ecommerce.entity;

import com.youtube.ecommerce.entity.User;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
//Tables and entities must not have same name
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private User user;
    @OneToOne
    private UserAddress userAddress;
    @OneToOne
    private Product product;
    @ElementCollection
    private Map<String, String> paymentMethod= new HashMap<>();

    private String deliveryMethod;

    private String paymentStatus;
    private String orderStatus;
    private Integer orderPrice;


    private String orderDate;

    public Order() {
    }

    public Order(User user, UserAddress userAddress, Product product, Map<String, String> paymentMethod, String deliveryMethod, String paymentStatus, String orderStatus, Integer orderPrice, String orderDate) {
        this.user = user;
        this.userAddress = userAddress;
        this.product = product;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
        this.orderPrice = orderPrice;
        this.orderDate = orderDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
