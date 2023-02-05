package io.modicon.userservice.service;

import io.modicon.ekatalogservice.api.dto.ItemDto;
import io.modicon.ekatalogservice.api.dto.ShopDto;
import io.modicon.userservice.api.dto.ItemWithPricesDto;
import io.modicon.userservice.model.ItemEntity;
import io.modicon.userservice.model.ShopEntity;

import java.util.List;

public class ItemMapper {

    public static ItemEntity mapToEntity(ItemDto itemDto, List<ShopDto> shops) {
        return ItemEntity.builder()
                .ref(itemDto.itemRef())
                .name(itemDto.name())
                .priceFrom(itemDto.priceFrom())
                .priceTo(itemDto.priceTo())
                .img(itemDto.img())
                .shops(shops.stream().map(ShopMapper::mapToEntity).toList())
                .build();
    }

    public static ItemWithPricesDto mapToDto(ItemEntity itemEntity) {
        return ItemWithPricesDto.builder()
                .ref(itemEntity.getRef())
                .name(itemEntity.getName())
                .priceFrom(itemEntity.getPriceFrom())
                .priceTo(itemEntity.getPriceTo())
                .img(itemEntity.getImg())
                .shops(itemEntity.getShops().stream().map(ShopMapper::mapToDto).toList())
                .build();
    }

}
