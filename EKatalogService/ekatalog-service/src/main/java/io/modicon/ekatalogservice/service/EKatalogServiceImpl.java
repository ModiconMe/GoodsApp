package io.modicon.ekatalogservice.service;

import io.modicon.ekatalogservice.api.dto.GetGoodsResponse;
import io.modicon.ekatalogservice.api.dto.GoodDto;

import java.util.ArrayList;

public class EKatalogServiceImpl implements EKatalogService {
    @Override
    public GetGoodsResponse getGoods(String goodName) {
        ArrayList<GoodDto> goods = new ArrayList<>();

        return new GetGoodsResponse(goods);
    }
}
