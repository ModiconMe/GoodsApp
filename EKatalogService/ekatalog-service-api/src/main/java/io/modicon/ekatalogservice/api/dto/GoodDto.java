package io.modicon.ekatalogservice.api.dto;

import java.math.BigDecimal;

public record GoodDto(
        String name,
        BigDecimal priceFrom,
        BigDecimal priceTo,
        String img
) {
}
