package com.youtube.ecommerce.service;

import com.youtube.ecommerce.configuration.JwtRequestFilter;
import com.youtube.ecommerce.dao.OrderDao;
import com.youtube.ecommerce.dao.ProductDao;
import com.youtube.ecommerce.dao.UserDao;
import com.youtube.ecommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    public void placeOrder(OrderRequest orderRequest) {
        List<Cart> cartList = orderRequest.getCart();

        //For each cart item, create an order
        for (Cart cart : cartList) {
            Product product = productDao.findById(cart.getProduct().getId()).get();
            String username = JwtRequestFilter.USERNAME;
            User user = userDao.findById(username).get();

            LocalDateTime now = LocalDateTime.now();

            // Format the date and time as a string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = now.format(formatter);

            Order order = new Order(
                    user,
                    orderRequest.getUserAddress(),
                    cart.getProduct(),
                    orderRequest.getPaymentMethod(),
                    "NORMAL",
                    "PAID",
                    "PENDING",
                    Integer.valueOf(product.getPrice().intValue()),
                    formattedDate
            );
            orderDao.save(order);
        }
    }

    public List<Order> getUserOrders() {
        String username = JwtRequestFilter.USERNAME;
        User user = userDao.findById(username).get();
        return orderDao.findByUser(user);
    }

    public List<Order> getAllOrders(){
        return (List<Order>) orderDao.findAll();
    }

}
