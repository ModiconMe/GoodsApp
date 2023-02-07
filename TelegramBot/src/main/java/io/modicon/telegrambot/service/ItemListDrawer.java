package io.modicon.telegrambot.service;


import io.modicon.client.dto.ItemDto;

import java.util.List;

public class ItemListDrawer {
    public static String draw(List<ItemDto> items) {
        StringBuilder sb = new StringBuilder();
        int pos = 1;
        for (ItemDto item : items) {
            if (item.priceFrom() != null || item.priceTo() != null) {
                sb.append(pos++).append(". ").append(item.name()).append("\n");
                if (item.priceFrom() != null)
                    sb.append("от ").append(item.priceFrom()).append("\n");
                if (item.priceTo() != null)
                    sb.append("до ").append(item.priceTo()).append("\n");
                sb.append(item.itemRef()).append("\n");
//                    sb.append("![item photo]").append("(").append(item.img()).append(")").append("\n");
            }
        }
        return sb.toString();
    }
}
