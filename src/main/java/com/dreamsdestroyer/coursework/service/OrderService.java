package com.dreamsdestroyer.coursework.service;

import com.dreamsdestroyer.coursework.api.model.OrderRequest;
import com.dreamsdestroyer.coursework.exception.ResourceNotFoundException;
import com.dreamsdestroyer.coursework.model.*;
import com.dreamsdestroyer.coursework.model.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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


    public OrderService(PurchaseOrderRepository purchaseOrderRepository, LocalUserRepository localUserRepository, AddressRepository addressRepository, ProductRepository productRepository, PurchaseOrderQuantitiesRepository purchaseOrderQuantitiesRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.localUserRepository = localUserRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
        this.purchaseOrderQuantitiesRepository = purchaseOrderQuantitiesRepository;
    }

    public List<PurchaseOrder> getOrders(Long id){
        return purchaseOrderRepository.findByUserId(id);
    }



    @Transactional
    public PurchaseOrder createOrder(OrderRequest orderRequest){
        System.out.println("Looking for user with ID: " + orderRequest.getUserId());
        LocalUser user = localUserRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        System.out.println("User found: " + user.getUsername());
        System.out.println("Looking for address with ID: " + orderRequest.getAddress().getId());
        Address address = addressRepository.findById(orderRequest.getAddress().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        System.out.println("Address found: " + orderRequest.getAddress().getId());

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setUser(user);
        purchaseOrder.setAddress(address);
        System.out.println("test");
        // Save the purchase order first to get its ID
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);

        PurchaseOrder finalPurchaseOrder = purchaseOrder;
        System.out.println("test2");
        List<PurchaseOrderQuantities> quantities = orderRequest.getQuantities().stream()
                .map(orderQuantities -> {
                    System.out.println("product id: ");
                    Optional<Product> product = productRepository.findById(orderQuantities.getProductId());

                    PurchaseOrderQuantities quantity = new PurchaseOrderQuantities();
                    quantity.setProduct(product.get());
                    quantity.setQuantity(orderQuantities.getQuantity());
                    quantity.setPurchaseOrder(finalPurchaseOrder);
                    return quantity;
                }).collect(Collectors.toList());

        purchaseOrderQuantitiesRepository.saveAll(quantities);

        purchaseOrder.setQuantities(quantities);

        return purchaseOrder;
    }
}
