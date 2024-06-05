package com.dreamsdestroyer.coursework.model.repository;

import com.dreamsdestroyer.coursework.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductById(Long id);

}
