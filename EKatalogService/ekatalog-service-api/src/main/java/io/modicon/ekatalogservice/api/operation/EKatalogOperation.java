package io.modicon.ekatalogservice.api.operation;

import io.modicon.ekatalogservice.api.dto.GetItemAndShopsResponse;
import io.modicon.ekatalogservice.api.dto.GetItemsResponse;
import io.modicon.ekatalogservice.api.dto.UrlDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface EKatalogOperation {
    @GetMapping("/{itemName}")
    GetItemsResponse getItems(@PathVariable String itemName);

    @PostMapping("/prices")
    GetItemAndShopsResponse getItemsWithPrices(@RequestBody UrlDto itemUrl);
}
