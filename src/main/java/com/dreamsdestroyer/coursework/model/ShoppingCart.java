package com.dreamsdestroyer.coursework.model;

import com.dreamsdestroyer.coursework.api.model.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "shoppingCart", optional = false, cascade = CascadeType.ALL)
    private LocalUser localUser;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();


}