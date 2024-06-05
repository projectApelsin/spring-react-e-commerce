package com.dreamsdestroyer.coursework.service;

import com.dreamsdestroyer.coursework.api.model.OrderRequest;
import com.dreamsdestroyer.coursework.exception.ResourceNotFoundException;
import com.dreamsdestroyer.coursework.model.*;
import com.dreamsdestroyer.coursework.model.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderService {

    private PurchaseOrderRepository purchaseOrderRepository;
    private LocalUserRepository localUserRepository;
    private AddressRepository addressRepository;
    private ProductRepository productRepository;
    private PurchaseOrderQuantitiesRepository purchaseOrderQuantitiesRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final InventoryRepository inventoryRepository;


    public OrderService(PurchaseOrderRepository purchaseOrderRepository, LocalUserRepository localUserRepository, AddressRepository addressRepository, ProductRepository productRepository, PurchaseOrderQuantitiesRepository purchaseOrderQuantitiesRepository,
                        ShoppingCartRepository shoppingCartRepository,
                        InventoryRepository inventoryRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.localUserRepository = localUserRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
        this.purchaseOrderQuantitiesRepository = purchaseOrderQuantitiesRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public List<PurchaseOrder> getOrders(Long id){
        return purchaseOrderRepository.findByUserId(id);
    }



    @Transactional
    public PurchaseOrder createOrder(OrderRequest orderRequest) {

        System.out.println("Received OrderRequest: " + orderRequest);
        System.out.println("UserId: " + orderRequest.getUserId());
        System.out.println("AddressId: " + (orderRequest.getAddress() != null ? orderRequest.getAddress().getId() : "null"));

        LocalUser user = localUserRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Address address = addressRepository.findById(orderRequest.getAddress().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setUser(user);
        purchaseOrder.setAddress(address);

        // Save the purchase order first to get its ID
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);

        // Get items from the shopping cart
        ShoppingCart shoppingCart = user.getShoppingCart();
        List<PurchaseOrderQuantities> quantities = new ArrayList<>(shoppingCart.getShoppingCartItems().size());

        // Convert ShoppingCartItems to PurchaseOrderQuantities
        for (ShoppingCartItem cartItem : shoppingCart.getShoppingCartItems()) {
            PurchaseOrderQuantities purchaseOrderQuantity = new PurchaseOrderQuantities();
            purchaseOrderQuantity.setProduct(cartItem.getProduct());
            purchaseOrderQuantity.setQuantity(cartItem.getQuantity());
            purchaseOrderQuantity.setPurchaseOrder(purchaseOrder);
            quantities.add(purchaseOrderQuantity);

            // Decrease inventory
            inventoryRepository.findByProduct(cartItem.getProduct()).ifPresent(inventory -> {
                inventory.decreaseQuantity(cartItem.getQuantity());
                inventoryRepository.save(inventory);
            });
        }

        // Save all quantities with the purchase order ID
        purchaseOrderQuantitiesRepository.saveAll(quantities);

        // Clear the shopping cart and save it
        shoppingCart.getShoppingCartItems().clear();
        shoppingCartRepository.save(shoppingCart);

        // Set the quantities in the purchase order and return it
        purchaseOrder.setQuantities(quantities);
        purchaseOrderRepository.save(purchaseOrder);

        return purchaseOrder;
    }
}
        /*List<PurchaseOrderQuantities> quantities = orderRequest.getQuantities().stream()
                .map(orderQuantities -> {
                    System.out.println("product id: ");
                    Optional<Product> product = productRepository.findById(orderQuantities.getProductId());

                    PurchaseOrderQuantities quantity = new PurchaseOrderQuantities();
                    quantity.setProduct(product.get());
                    quantity.setQuantity(orderQuantities.getQuantity());
                    quantity.setPurchaseOrder(finalPurchaseOrder);
                    return quantity;
                }).collect(Collectors.toList());
*/