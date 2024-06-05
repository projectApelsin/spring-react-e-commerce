package com.dreamsdestroyer.coursework.model.repository;

import com.dreamsdestroyer.coursework.model.Inventory;
import com.dreamsdestroyer.coursework.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProduct(Product product);
}