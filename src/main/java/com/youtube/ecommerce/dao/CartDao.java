package com.youtube.ecommerce.dao;

import com.youtube.ecommerce.entity.Cart;
import com.youtube.ecommerce.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartDao extends CrudRepository<Cart, Integer> {
public List<Cart> findByUser(User user);

}
