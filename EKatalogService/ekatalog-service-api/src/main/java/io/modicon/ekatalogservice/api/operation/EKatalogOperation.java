package io.modicon.ekatalogservice.api.operation;

import io.modicon.ekatalogservice.api.dto.GetItemAndShopsResponse;
import io.modicon.ekatalogservice.api.dto.GetItemsResponse;
import io.modicon.ekatalogservice.api.dto.UrlDto;
import org.springframework.web.bind.annotation.*;

public interface EKatalogOperation {

    String route = "api/items";

    @GetMapping(route + "/{itemName}")
    GetItemsResponse getItems(@PathVariable String itemName);

    @PostMapping(route + "/prices")
    GetItemAndShopsResponse getItemsWithPrices(@RequestBody UrlDto itemUrl);
}
