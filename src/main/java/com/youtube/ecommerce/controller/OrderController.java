package com.youtube.ecommerce.controller;

import com.youtube.ecommerce.entity.Order;
import com.youtube.ecommerce.entity.OrderRequest;
import com.youtube.ecommerce.entity.Product;
import com.youtube.ecommerce.entity.User;
import com.youtube.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/order")
    public void placeOrder(
            @RequestBody OrderRequest orderRequest
    ){
        orderService.placeOrder(orderRequest);
    }
    @GetMapping("/getUserOrders")
    public List<Order> getUserOrders(){
        return orderService.getUserOrders();
    }
    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/getUsersWithMostOrders")
    public List<User> getUsersWithMostOrders(){
        return orderService.getUsersWithMostOrders(2);
    }

    @GetMapping("/getProductsWithMostOrders")
    public List<Product> getProductsWithMostOrders(){
        return orderService.getMostOrderedProducts(1);
    }
}
