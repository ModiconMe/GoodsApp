package io.modicon.ekatalogservice.api.operation;

import io.modicon.ekatalogservice.api.dto.GetGoodsResponse;
import io.modicon.ekatalogservice.api.dto.GetPricesResponse;
import io.modicon.ekatalogservice.api.dto.UrlDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface EKatalogOperation {
    @GetMapping("{goodName}")
    GetGoodsResponse getGoods(@PathVariable String goodName);

    @PostMapping("/prices")
    GetPricesResponse getPrices(@RequestBody UrlDto goodUrl);
}
