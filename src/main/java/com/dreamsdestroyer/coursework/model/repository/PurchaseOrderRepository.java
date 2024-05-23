package com.dreamsdestroyer.coursework.model.repository;


import com.dreamsdestroyer.coursework.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    List<PurchaseOrder> findByUserId(Long id);
}
