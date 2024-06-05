package com.dreamsdestroyer.coursework.model.repository;


import com.dreamsdestroyer.coursework.model.PurchaseOrderQuantities;
import com.dreamsdestroyer.coursework.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderQuantitiesRepository extends JpaRepository<PurchaseOrderQuantities, Long> {

}