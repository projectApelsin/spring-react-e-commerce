package com.dreamsdestroyer.coursework.api.model;


import com.dreamsdestroyer.coursework.model.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderRequest {
    private Long userId;
    private Address address;
    private List<OrderItem> quantities;
}
