package com.youtube.ecommerce.service;

import com.youtube.ecommerce.configuration.JwtRequestFilter;
import com.youtube.ecommerce.dao.OrderDao;
import com.youtube.ecommerce.dao.ProductDao;
import com.youtube.ecommerce.dao.UserDao;
import com.youtube.ecommerce.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private ProductDao productDao;

    @Mock
    private UserDao userDao;

    @Mock
    private OrderDao orderDao;


    private OrderService orderService;

    @BeforeEach
    void setUp() throws Exception {
        orderService = new OrderService(orderDao, productDao, userDao);
    }
    private Product initTestProduct() {
        Category category = new Category("Electronics");
        BigDecimal price = BigDecimal.valueOf(5);
        Product product = new Product("iPhone 13 Pro",price, category, null, null, null, null, null);
        return product;
    }
    private User initTestUser(){
        String username = "testuser";
        User user = new User(
                username,
                "Test",
                "User",
                "test@example.com",
                "password123",
                null,
                "1990-01-01");
        return user;
    }

    @Test
    @Disabled
    void placeOrder() {
        //Create new product
        Product product = initTestProduct();
        User user = initTestUser();
        UserAddress userAddress = new UserAddress("USA", "New York", "10001", "5th Ave", "1", "Apt 10", null);

        // Create a mock order request
        Cart cart = new Cart(
                user,
                product
        );
        List<Cart> cartList = Arrays.asList(cart);

        OrderRequest orderRequest = new OrderRequest(
            userAddress,
            cartList,
            null,
                null
        );

        // Create mock objects for the product and user
        when(productDao.findById(product.getId())).thenReturn(Optional.of(product));

        when(userDao.findById(user.getUserName())).thenReturn(Optional.of(user));
        JwtRequestFilter.USERNAME = user.getUserName();


        // Call the placeOrder method
        orderService.placeOrder(orderRequest);

        // Verify that the order was saved
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        BigDecimal price = BigDecimal.valueOf(5);

        Order expectedOrder = new Order(
                user,
                userAddress,
                product,
                null,
                "NORMAL",
                "PAID",
                "PENDING",
                price.intValue(),
                formattedDate
        );
        assertEquals(expectedOrder, orderDao.save(expectedOrder));
    }

    @Test
    void getUserOrders() {
    }

    @Test
    void updateOrderStatus() {
    }

    @Test
    void getAllOrders() {
    }

    @Test
    void getUsersWithMostOrders() {
    }

    @Test
    void getMostOrderedProducts() {
    }
}