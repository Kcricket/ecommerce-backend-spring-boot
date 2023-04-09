package com.youtube.ecommerce.service;

import com.youtube.ecommerce.dao.CategoryDao;
import com.youtube.ecommerce.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryDao categoryDao;

    private CategoryService categoryService;

    @BeforeEach
    void setUp() throws Exception {
        categoryService = new CategoryService(categoryDao);
    }

    @Test
    void addNewCategory() {
        Category category = new Category();
        category.setName("Test Category");

        when(categoryDao.save(category)).thenReturn(category);

        Category result = categoryService.addNewCategory(category);

        verify(categoryDao, times(1)).save(category);
        assertEquals("Test Category", result.getName());
    }

    @Test
    void getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        Category category1 = new Category();
        category1.setName("Category 1");
        Category category2 = new Category();
        category2.setName("Category 2");
        categories.add(category1);
        categories.add(category2);

        when(categoryDao.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();

        verify(categoryDao, times(1)).findAll();
        assertEquals(2, result.size());
        assertEquals("Category 1", result.get(0).getName());
        assertEquals("Category 2", result.get(1).getName());
    }

    @Test
    void findByName() {
        String name = "Test Category";
        Category category = new Category();
        category.setName(name);

        when(categoryDao.findByName(name)).thenReturn(category);

        Category result = categoryService.findByName(name);

        verify(categoryDao, times(1)).findByName(name);
        assertEquals(name, result.getName());
    }

}