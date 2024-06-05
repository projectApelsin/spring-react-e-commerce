package com.dreamsdestroyer.coursework.api.controller.shoppingCartController;


import com.dreamsdestroyer.coursework.api.model.OrderItem;
import com.dreamsdestroyer.coursework.api.model.ShoppingCartItemRequest;
import com.dreamsdestroyer.coursework.model.ShoppingCart;
import com.dreamsdestroyer.coursework.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public ResponseEntity createShoppingCartItem(@RequestBody OrderItem orderItem){
        shoppingCartService.addShoppingCartItem(orderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItem);
    }

    @GetMapping
    public ResponseEntity<ShoppingCart> getAllShoppingCartItem(@RequestParam Long id){
        return ResponseEntity.ok(shoppingCartService.getAllShoppingCartItem(id));
    }

    @PutMapping
    public ResponseEntity changeShoppingCartItemQuantity(@RequestBody ShoppingCartItemRequest shoppingCartItemRequest){
        shoppingCartService.changeShoppingCartItemQuantity(shoppingCartItemRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity deleteShoppingCartItem(@RequestParam Long id){
        shoppingCartService.deleteShoppingCartItem(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
