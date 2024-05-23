/*package com.dreamsdestroyer.CourseWork.api.controller.order;

import com.dreamsdestroyer.CourseWork.api.model.OrderRequest;
import com.dreamsdestroyer.CourseWork.model.LocalUser;
import com.dreamsdestroyer.CourseWork.model.PurchaseOrder;
import com.dreamsdestroyer.CourseWork.model.PurchaseOrderQuantities;
import com.dreamsdestroyer.CourseWork.service.OrderService;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //@GetMapping()
   // public List<PurchaseOrder> getOrders(Long id){
      //  return orderService.getOrders(id);
   // }

    @PostMapping()
    public ResponseEntity<PurchaseOrder> createOrder(@RequestBody OrderRequest orderRequest){
        //PurchaseOrder order = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}
*/