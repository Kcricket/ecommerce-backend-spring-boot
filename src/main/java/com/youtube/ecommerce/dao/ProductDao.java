package com.youtube.ecommerce.dao;

import com.youtube.ecommerce.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {
    List<Product> findByCategoryName(String categoryName);
    List<Product> findAll();

    List<Product> findByTitleContainingIgnoreCase(String title);
}
