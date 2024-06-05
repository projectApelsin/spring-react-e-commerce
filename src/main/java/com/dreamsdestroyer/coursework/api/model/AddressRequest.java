package com.dreamsdestroyer.coursework.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private Long userId;
}
