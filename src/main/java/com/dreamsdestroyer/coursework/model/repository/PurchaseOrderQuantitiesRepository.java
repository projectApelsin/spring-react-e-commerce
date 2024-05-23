package com.dreamsdestroyer.coursework.model.repository;


import com.dreamsdestroyer.coursework.model.PurchaseOrderQuantities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PurchaseOrderQuantitiesRepository extends JpaRepository<PurchaseOrderQuantities, Long> {

}