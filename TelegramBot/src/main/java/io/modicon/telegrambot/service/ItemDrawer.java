package io.modicon.telegrambot.service;


import io.modicon.client.dto.ItemDto;
import io.modicon.client.dto.ShopDto;

import java.util.List;

public class ItemDrawer {
    public static String draw(ItemDto item, List<ShopDto> shops) {
        StringBuilder sb = new StringBuilder();
        if (item != null) {
            sb.append(item.name()).append("\n").append(item.itemRef());
            int pos = 1;
            if (shops != null && !shops.isEmpty()) {
                sb.append("\n").append("Продается в следующих магазинах: ").append("\n");
                for (ShopDto shop : shops) {
                    sb.append(pos++).append(". ")
                            .append(shop.shopRef()).append(", ")
                            .append(shop.price()).append(" руб").append("\n");
                }
            }
            return sb.toString();
        }
        return "Не найдены магазины в которых продается данный товар";
    }
}
