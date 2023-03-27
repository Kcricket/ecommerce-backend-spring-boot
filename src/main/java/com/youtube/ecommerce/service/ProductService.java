package com.youtube.ecommerce.service;

import com.youtube.ecommerce.dao.ProductDao;
import com.youtube.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;


    public Product addNewProduct(Product product){
        return productDao.save(product);
    }
    public List<Product> findByCategoryName(String categoryName) {
        return productDao.findByCategoryName(categoryName);
    }

    public List<Product> getAllProducts(String searchKey) {
        if(searchKey.equals("")){
            return (List<Product>) productDao.findAll();
        }else{
            return productDao.findByTitleContainingIgnoreCase(searchKey);
        }
    }

    public ProductService() {
    }

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
