package io.modicon.userservice.api.dto;

import io.modicon.ekatalogservice.api.dto.ShopDto;
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
