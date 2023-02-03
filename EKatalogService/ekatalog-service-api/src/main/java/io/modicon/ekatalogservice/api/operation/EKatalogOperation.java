package io.modicon.ekatalogservice.api.operation;

import io.modicon.ekatalogservice.api.dto.GetGoodsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface EKatalogOperation {
    @GetMapping("${goodName}")
    GetGoodsResponse getGoods(@PathVariable String goodName);
}
