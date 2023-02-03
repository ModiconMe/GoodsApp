package io.modicon.ekatalogservice.api.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record GoodDto(
        String name,
        String ref,
        BigDecimal priceFrom,
        BigDecimal priceTo,
        String img
) {
}
