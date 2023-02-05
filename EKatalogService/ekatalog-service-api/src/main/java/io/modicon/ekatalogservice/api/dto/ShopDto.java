package io.modicon.ekatalogservice.api.dto;

import java.math.BigDecimal;

public record ShopDto(
        String shopRef,
        BigDecimal price
) {
}
