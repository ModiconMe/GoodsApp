package io.modicon.client.operation;

import io.modicon.client.dto.GetItemAndShopsResponse;
import io.modicon.client.dto.GetItemsResponse;
import io.modicon.client.dto.UrlDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface EKatalogOperation {

    String route = "api/items";

    @GetMapping(route + "/{itemName}")
    GetItemsResponse getItems(@PathVariable String itemName);

    @PostMapping(route + "/prices")
    GetItemAndShopsResponse getItemsWithPrices(@RequestBody UrlDto itemUrl);
}
