package com.dreamsdestroyer.coursework.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Long productId;
    private int quantity;
}
