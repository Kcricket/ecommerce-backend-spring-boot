package com.youtube.ecommerce.dao;

import com.youtube.ecommerce.entity.Order;
import com.youtube.ecommerce.entity.Product;
import com.youtube.ecommerce.entity.Role;
import com.youtube.ecommerce.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class OrderDaoTest {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;


    public void initObjects(){
        User user1 = new User(
                "user1",
                "John",
                "Doe",
                "johndoe@example.com",
                "password123",
                null,
                "1990-01-01"
        );

        User user2 = new User(
                "user2",
                "Jane",
                "Doe",
                null,
                "password123",
                null,
                "1990-01-01"
        );


        userDao.save(user1);
        userDao.save(user2);
        Product product = new Product(
                "Product 1",
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        productDao.save(product);
        Order order1 = new Order(
                user1,
                null,
                product,
                null,
                null,
                null,
                null,
                null,
                null
        );
        Order order2 = new Order(
                user1,
                null,
                product,
                null,
                null,
                null,
                null,
                null,
                null
        );

        Order order3 = new Order(
                user2,
                null,
                product,
                null,
                null,
                null,
                null,
                null,
                null
        );

        orderDao.save(order1);
        orderDao.save(order2);
        orderDao.save(order3);
    }

    @Test
    void itShouldCountOrdersByUser() {
        //given
        initObjects();

        //when
        List<User[]> ordersByUser = orderDao.countOrdersByUser();

        //Length should be 2 because we have 2 users
        Assertions.assertEquals(2, ordersByUser.size());
        //First should be user1, because has more orders
        Assertions.assertEquals("user1", ordersByUser.get(0)[0].getUserName());

    }

    @Test
    void countProductsOrdered() {
        //given
        User user = new User(
                "user1",
                "John",
                "Doe",
                "johndoe@example.com",
                "password123",
                null,
                "1990-01-01"
        );
        Product product1 = new Product(
                "Product 1",
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        Product product2 = new Product(
                "Product 2",
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        userDao.save(user);
        productDao.save(product1);
        productDao.save(product2);
        Order order1 = new Order(
                user,
                null,
                product1,
                null,
                null,
                null,
                null,
                null,
                null
        );
        Order order2 = new Order(
                user,
                null,
                product1,
                null,
                null,
                null,
                null,
                null,
                null
        );
        Order order3 = new Order(
                user,
                null,
                product2,
                null,
                null,
                null,
                null,
                null,
                null
        );
        orderDao.save(order1);
        orderDao.save(order2);
        orderDao.save(order3);
        //when
        List<Product[]> productsOrdered = orderDao.countProductsOrdered();
        //then
        //Length should be 2 because we have 2 products
        Assertions.assertEquals(2, productsOrdered.size());
        //First and most ordered product should be product1
        Assertions.assertEquals("Product 1", productsOrdered.get(0)[0].getTitle());
        //Second and less ordered product should be product2
        Assertions.assertEquals("Product 2", productsOrdered.get(1)[0].getTitle());
    }

}