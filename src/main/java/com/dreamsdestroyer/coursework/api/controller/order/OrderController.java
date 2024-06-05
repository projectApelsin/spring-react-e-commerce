package com.dreamsdestroyer.coursework.api.controller.order;


import com.dreamsdestroyer.coursework.api.model.OrderRequest;
import com.dreamsdestroyer.coursework.model.PurchaseOrder;
import com.dreamsdestroyer.coursework.service.OrderService;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping()
    public ResponseEntity<PurchaseOrder> createOrder(@RequestBody OrderRequest orderRequest) {
        PurchaseOrder order = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}
