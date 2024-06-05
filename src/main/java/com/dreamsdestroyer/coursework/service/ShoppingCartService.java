package com.dreamsdestroyer.coursework.service;


import com.dreamsdestroyer.coursework.api.model.OrderItem;
import com.dreamsdestroyer.coursework.api.model.ShoppingCartItemRequest;
import com.dreamsdestroyer.coursework.model.ShoppingCart;
import com.dreamsdestroyer.coursework.model.ShoppingCartItem;
import com.dreamsdestroyer.coursework.model.repository.ProductRepository;
import com.dreamsdestroyer.coursework.model.repository.ShoppingCartItemRepository;
import com.dreamsdestroyer.coursework.model.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;


    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository,
                                ShoppingCartItemRepository shoppingCartItemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
    }

    public void addShoppingCartItem(OrderItem orderItem){
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setProduct(productRepository.getProductById(orderItem.getProductId()));
        shoppingCartItem.setQuantity(orderItem.getQuantity());
        shoppingCartItem.setShoppingCart(shoppingCartRepository.findByLocalUser_Id(orderItem.getUserId()));
        shoppingCartItemRepository.save(shoppingCartItem);
    }

    public ShoppingCart getAllShoppingCartItem(Long id){
        return shoppingCartRepository.findById(id).get();
    }

    public void changeShoppingCartItemQuantity(ShoppingCartItemRequest shoppingCartItemRequest){
        System.out.println("Id: " + shoppingCartItemRequest.getId() + "Quantity: " + shoppingCartItemRequest.getQuantity());
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(shoppingCartItemRequest.getId()).get();
        shoppingCartItem.setQuantity(shoppingCartItemRequest.getQuantity());
        System.out.println("Id: " + shoppingCartItem.getId() + "Quantity: " + shoppingCartItem.getQuantity());
        shoppingCartItemRepository.save(shoppingCartItem);

    }

    public void deleteShoppingCartItem(Long id) {
        shoppingCartItemRepository.deleteById(id);
    }
}
