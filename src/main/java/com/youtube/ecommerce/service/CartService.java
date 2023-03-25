package com.youtube.ecommerce.service;

import com.youtube.ecommerce.configuration.JwtRequestFilter;
import com.youtube.ecommerce.dao.CartDao;
import com.youtube.ecommerce.dao.ProductDao;
import com.youtube.ecommerce.dao.UserDao;
import com.youtube.ecommerce.entity.Cart;
import com.youtube.ecommerce.entity.Product;
import com.youtube.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    public Cart addToCart(Integer productId){
        Product product = productDao.findById(productId).get();
        String username = JwtRequestFilter.USERNAME;
        User user = null;
        if (username != null){
            user = userDao.findById(username).get();

        }

        if (product != null && user != null){
            Cart cart = new Cart(user, product);
            return cartDao.save(cart);
        }
        return null;
    }

    public void removeCartById(Integer cartId){
        cartDao.deleteById(cartId);
    }
    public List<Cart> getCartItems(){
        String username = JwtRequestFilter.USERNAME;
        User user = userDao.findById(username).get();
        return cartDao.findByUser(user);
    }
}
