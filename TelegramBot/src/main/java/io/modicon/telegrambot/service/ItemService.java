package io.modicon.telegrambot.service;

import io.modicon.ekatalogservice.api.dto.GetItemAndShopsResponse;
import io.modicon.ekatalogservice.api.dto.ItemDto;
import io.modicon.ekatalogservice.api.dto.ShopDto;
import io.modicon.ekatalogservice.api.dto.UrlDto;
import io.modicon.telegrambot.client.EKatalogServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final EKatalogServiceClient eKatalogServiceClient;

    public List<ItemDto> findItems(String itemName) {
        return eKatalogServiceClient.getItems(itemName).getItems();
    }

    public GetItemAndShopsResponse getPrices(String url) {
        return eKatalogServiceClient.getItemsWithPrices(new UrlDto(url));
    }

}
