package io.modicon.ekatalogservice.service;

import io.modicon.ekatalogservice.api.dto.GetGoodsResponse;

public interface EKatalogService {
    GetGoodsResponse getGoods(String goodName);
}
