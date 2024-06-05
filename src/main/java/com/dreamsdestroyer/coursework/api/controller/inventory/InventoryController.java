package com.dreamsdestroyer.coursework.api.controller.inventory;

import com.dreamsdestroyer.coursework.api.model.OrderItem;
import com.dreamsdestroyer.coursework.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PutMapping
    public ResponseEntity decreaseQuantity(@RequestBody OrderItem orderItem) {
        inventoryService.decreaseQuantity(orderItem);
        return ResponseEntity.ok().build();
    }

}
