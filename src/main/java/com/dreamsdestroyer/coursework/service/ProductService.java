package com.dreamsdestroyer.coursework.service;


import com.dreamsdestroyer.coursework.model.Product;
import com.dreamsdestroyer.coursework.model.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
        return productRepository.getProductById(id);
    }

}
