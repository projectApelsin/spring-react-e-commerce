package com.dreamsdestroyer.coursework.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartItemRequest {
    private Long id;
    private int quantity;
}
