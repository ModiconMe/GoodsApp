package io.modicon.client.dto;

import java.math.BigDecimal;

public record ShopDto(
        String shopRef,
        BigDecimal price
) {
}
