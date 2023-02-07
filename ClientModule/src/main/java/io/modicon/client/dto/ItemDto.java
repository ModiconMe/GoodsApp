package io.modicon.client.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ItemDto(
        String name,
        String itemRef,
        BigDecimal priceFrom,
        BigDecimal priceTo,
        String img
) {
}
