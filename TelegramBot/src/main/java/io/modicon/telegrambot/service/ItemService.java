package io.modicon.telegrambot.service;

import io.modicon.client.dto.GetItemAndShopsResponse;
import io.modicon.client.dto.ItemDto;
import io.modicon.client.dto.UrlDto;
import io.modicon.client.feign.EKatalogServiceClient;
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
