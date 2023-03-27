package com.youtube.ecommerce.service;

import com.youtube.ecommerce.dao.ProductDao;
import com.youtube.ecommerce.entity.Category;
import com.youtube.ecommerce.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductDao productDao;
    private ProductService productService;
    @BeforeEach
    void setUp() {
        productService = new ProductService(productDao);
    }
    private Product initTestProduct() {
        Category category = new Category("Electronics");
        Product product = new Product("iPhone 13 Pro",null, category, null, null, null, null, null);
        return product;
    }

    @Test
    void addNewProduct() {
        //Given a Product
        Product product = initTestProduct();

        when(productDao.save(product)).thenReturn(product);
        //When
        Product result = productService.addNewProduct(product);

        //Then
        assertEquals(product, result);
        verify(productDao).save(product);
    }

    @Test
    void findByCategoryName() {
        //Given
        String categoryName = "Electronics";
        Product product = initTestProduct();
        product.setCategory(new Category(categoryName));
        Product product2 = initTestProduct();
        product2.setCategory(new Category(categoryName));
        List<Product> products = Arrays.asList(product, product2);

        when(productDao.findByCategoryName(categoryName)).thenReturn(products);
        //When
        List<Product> result = productService.findByCategoryName(categoryName);
        //Then
        assertEquals(products, result);
        verify(productDao).findByCategoryName(categoryName);

    }

    @Test
    void getAllProducts() {
        //Given
        String searchKey = "iPhone";
        Product product = initTestProduct();
        product.setTitle(searchKey);
        Product product2 = initTestProduct();
        product2.setTitle(searchKey);
        List<Product> products = Arrays.asList(product, product2);

        when(productDao.findByTitleContainingIgnoreCase(searchKey)).thenReturn(products);
        //When
        List<Product> result = productService.getAllProducts(searchKey);
        //Then
        assertEquals(products, result);
        verify(productDao).findByTitleContainingIgnoreCase(searchKey);
    }
}