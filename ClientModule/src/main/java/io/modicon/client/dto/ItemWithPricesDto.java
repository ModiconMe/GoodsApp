package io.modicon.client.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ItemWithPricesDto(
        String ref,
        String name,
        BigDecimal priceFrom,
        BigDecimal priceTo,
        String img,
        List<ShopDto> shops
) {
}
