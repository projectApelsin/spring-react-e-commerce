package com.dreamsdestroyer.coursework.model.repository;

import com.dreamsdestroyer.coursework.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByLocalUser_Id(Long id);
}