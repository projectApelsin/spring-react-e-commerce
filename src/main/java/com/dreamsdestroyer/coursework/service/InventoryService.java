package com.dreamsdestroyer.coursework.service;

import com.dreamsdestroyer.coursework.api.model.OrderItem;
import com.dreamsdestroyer.coursework.model.Inventory;
import com.dreamsdestroyer.coursework.model.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;
    private ProductService productService;

    public InventoryService(InventoryRepository inventoryRepository, ProductService productService) {
        this.inventoryRepository = inventoryRepository;
        this.productService = productService;
    }

    public void decreaseQuantity(OrderItem orderItem) {
        Inventory inventory = inventoryRepository.findByProduct(productService.getProductById(orderItem.getProductId())).get();
        System.out.println(inventory.decreaseQuantity(orderItem.getQuantity()));
        inventoryRepository.save(inventory);
    }

}
