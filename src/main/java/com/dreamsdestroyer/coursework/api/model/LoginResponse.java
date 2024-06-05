package com.dreamsdestroyer.coursework.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class LoginResponse {
    @Getter
    @Setter
    @JsonProperty("success_token")
    private String successToken;

    @Getter
    @Setter
    private boolean success;

    @Getter
    @Setter
    private String failureReason;

    @Getter
    @Setter
    private Long userId;

    @Getter
    @Setter
    private Long cartId;

}
