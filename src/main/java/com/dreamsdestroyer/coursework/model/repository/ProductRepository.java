package com.dreamsdestroyer.coursework.model.repository;

import com.dreamsdestroyer.coursework.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductById(Long id);

}
