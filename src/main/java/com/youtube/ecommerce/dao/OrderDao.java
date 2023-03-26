package com.youtube.ecommerce.dao;

import com.youtube.ecommerce.entity.Order;
import com.youtube.ecommerce.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDao extends CrudRepository<Order, Integer> {
    public List<Order> findByUser(User user);
}
