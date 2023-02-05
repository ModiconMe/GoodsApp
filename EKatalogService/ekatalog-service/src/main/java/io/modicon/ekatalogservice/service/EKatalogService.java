package io.modicon.ekatalogservice.service;

import io.modicon.ekatalogservice.api.dto.GetItemsResponse;
import io.modicon.ekatalogservice.api.dto.GetItemAndShopsResponse;

public interface EKatalogService {
    GetItemsResponse getItems(String itemName);

    GetItemAndShopsResponse getPrices(String itemUrl);
}
