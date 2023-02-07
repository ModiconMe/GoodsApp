package io.modicon.telegrambot.service;


import io.modicon.client.dto.ItemWithPricesDto;
import io.modicon.client.dto.ShopDto;

import java.util.List;

public class ItemWithPricesDrawer {

    public static String draw(List<ItemWithPricesDto> items) {
        StringBuilder sb = new StringBuilder();
        if (items != null && !items.isEmpty()) {
            int itemPos = 1;
            for (ItemWithPricesDto item : items) {
                sb.append(itemPos++).append(". ").append(item.name()).append("\n");
                if (item.priceFrom() != null)
                    sb.append("от ").append(item.priceFrom()).append("\n");
                if (item.priceTo() != null)
                    sb.append("до ").append(item.priceTo()).append("\n");
                sb.append(item.ref()).append("\n");
                List<ShopDto> shops = item.shops();
                if (shops != null && !shops.isEmpty()) {
                    int shopPos = 1;
                    sb.append("Продается в следующих магазинах: ").append("\n");
                    for (ShopDto shop : shops) {
                        sb.append(shopPos++).append(". ")
                                .append(shop.shopRef()).append(", ")
                                .append(shop.price()).append(" руб").append("\n");
                    }
                }
            }
            return sb.toString();
        }
        return "Вы пока не отслеживаете ни одного товара";
    }
}
