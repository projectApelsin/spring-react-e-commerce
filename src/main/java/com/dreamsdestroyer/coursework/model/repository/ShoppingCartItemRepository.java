package com.dreamsdestroyer.coursework.model.repository;

import com.dreamsdestroyer.coursework.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    Optional<ShoppingCartItem> findById(Long id);
}