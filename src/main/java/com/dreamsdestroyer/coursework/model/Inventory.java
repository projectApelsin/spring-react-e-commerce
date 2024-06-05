package com.dreamsdestroyer.coursework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public String decreaseQuantity(int quantity) {
        if (this.quantity - quantity > 0) {
            this.quantity -= quantity;
            return "Success";
        }
        return "Failed. Not enough products";
    }
}