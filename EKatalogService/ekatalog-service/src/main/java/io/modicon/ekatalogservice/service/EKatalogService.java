package io.modicon.ekatalogservice.service;

import io.modicon.ekatalogservice.api.dto.GetGoodsResponse;
import io.modicon.ekatalogservice.api.dto.GetPricesResponse;

public interface EKatalogService {
    GetGoodsResponse getGoods(String goodName);

    GetPricesResponse getPrices(String goodUrl);
}
