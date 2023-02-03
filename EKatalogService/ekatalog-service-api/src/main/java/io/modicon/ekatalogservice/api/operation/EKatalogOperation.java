package io.modicon.ekatalogservice.api.operation;

import io.modicon.ekatalogservice.api.dto.GetGoodsResponse;

public interface EKatalogOperation {
    GetGoodsResponse getGoods(String goodName);
}
