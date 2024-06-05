package com.dreamsdestroyer.coursework.service;

import com.dreamsdestroyer.coursework.api.model.OrderItem;
import com.dreamsdestroyer.coursework.model.PurchaseOrderQuantities;
import com.dreamsdestroyer.coursework.model.repository.ProductRepository;
import com.dreamsdestroyer.coursework.model.repository.PurchaseOrderQuantitiesRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderQuantitiesService {

    private PurchaseOrderQuantitiesRepository purchaseOrderQuantitiesRepository;
    private ProductRepository productRepository;

    public OrderQuantitiesService(PurchaseOrderQuantitiesRepository purchaseOrderQuantitiesRepository, ProductRepository productRepository) {
        this.purchaseOrderQuantitiesRepository = purchaseOrderQuantitiesRepository;
        this.productRepository = productRepository;
    }


}

