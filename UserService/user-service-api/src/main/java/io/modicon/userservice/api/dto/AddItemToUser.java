package io.modicon.userservice.api.dto;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class AddItemToUser {
    public String itemUrl;
    public BigDecimal price;
}
