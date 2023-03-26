package com.youtube.ecommerce.dao;

import com.youtube.ecommerce.entity.Order;
import com.youtube.ecommerce.entity.Product;
import com.youtube.ecommerce.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDao extends CrudRepository<Order, Integer> {
    public List<Order> findByUser(User user);

    @Query("SELECT o.user, COUNT(o.id) as orderCount " +
            "FROM Order o " +
            "GROUP BY o.user " +
            "ORDER BY orderCount DESC")
    public List<User[]> countOrdersByUser();
    @Query("SELECT o.product, COUNT(o.id) as orderCount " +
            "FROM Order o " +
            "GROUP BY o.product " +
            "ORDER BY orderCount DESC")
    List<Product[]> countProductsOrdered();
}
