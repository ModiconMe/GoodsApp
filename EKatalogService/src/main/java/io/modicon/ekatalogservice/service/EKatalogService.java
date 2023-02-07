package io.modicon.ekatalogservice.service;

import io.modicon.client.dto.GetItemAndShopsResponse;
import io.modicon.client.dto.GetItemsResponse;

public interface EKatalogService {
    GetItemsResponse getItems(String itemName);

    GetItemAndShopsResponse getPrices(String itemUrl);
}
