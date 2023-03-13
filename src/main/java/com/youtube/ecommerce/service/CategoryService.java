package com.youtube.ecommerce.service;

import com.youtube.ecommerce.dao.CategoryDao;
import com.youtube.ecommerce.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    public Category addNewCategory(Category category){
        return categoryDao.save(category);
    }

    public List<Category> getAllCategories() {
        return (List<Category>) categoryDao.findAll();
    }
    public Category findByName(String name) {
        return categoryDao.findByName(name);
    }
}
