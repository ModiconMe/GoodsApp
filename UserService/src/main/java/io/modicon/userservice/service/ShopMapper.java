package io.modicon.userservice.service;

import io.modicon.client.dto.ShopDto;
import io.modicon.userservice.model.ShopEntity;

public class ShopMapper {
    public static ShopEntity mapToEntity(ShopDto shopDto) {
        return new ShopEntity(shopDto.shopRef(), shopDto.price());
    }

    public static ShopDto mapToDto(ShopEntity shop) {
        return new ShopDto(shop.getShopRef(), shop.getPrice());
    }
}
