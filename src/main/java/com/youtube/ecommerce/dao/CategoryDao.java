package com.youtube.ecommerce.dao;

import com.youtube.ecommerce.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends CrudRepository<Category, Integer> {
    Category findByName(String name);

}
