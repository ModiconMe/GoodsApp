package io.modicon.userservice.api.dto;

import java.math.BigDecimal;

public record UserItemPriceDto(
        String userId,
        String itemId,
        BigDecimal priceToCheck,
        Boolean happened
) {
}
